package py.edu.una.rest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.edu.una.rest.model.UltimaFactura;
import py.edu.una.rest.services.UltimaFacturaService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("ultima-factura")
public class UltimaFacturaControllers {
	
	@Autowired
	private UltimaFacturaService service;

	public static final Logger logger = LoggerFactory.getLogger(UltimaFacturaControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listar(@RequestParam(required = false) String usuario) {
		if(usuario == null ) {
			logger.info("Ejecutando Listar");
			List<UltimaFactura> uFacturas = service.listar();
			if (uFacturas.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			} else {
				try {
					logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(uFacturas));
				} catch (JsonProcessingException e) {
				}
				return new ResponseEntity<List<UltimaFactura>>(uFacturas, HttpStatus.OK);
			}
		}else {
			logger.info("Ejecutando getByUsuario");
			UltimaFactura uFactura = service.getByUsuario(usuario);
			if(uFactura == null) {
				return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay ultima factura para el sgte Usuario " + usuario) ,HttpStatus.NO_CONTENT);
			}else {
				try {
					logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(uFactura));
				} catch (JsonProcessingException e) {
				}
				return new ResponseEntity<UltimaFactura>(uFactura, HttpStatus.OK);
			}
		}
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		UltimaFactura uFactura = service.getById(id);
		if (uFactura == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay en UltimaFactura el siguiente producto con ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<UltimaFactura>(uFactura, HttpStatus.OK);
		}
	}

}
