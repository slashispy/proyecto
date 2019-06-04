package py.edu.una.rest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.edu.una.rest.model.Compra;
import py.edu.una.rest.services.CompraService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("compra")
public class CompraControllers {
	
	@Autowired
	private CompraService service;

	public static final Logger logger = LoggerFactory.getLogger(CompraControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarCompras(@RequestParam(required = false) String estado){
		logger.info("Ejecutando Listar");
		List<Compra> compras;
		if (estado == null) {
			compras = service.listar();	
		}else {
			compras = service.getByEstado(estado);
		}
		if (compras.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearCompra(@RequestBody Compra compra){
		try {
			logger.info("Creando el Compra : {}", mapper.writeValueAsString(compra));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Compra res = service.insertar(compra);
		return new ResponseEntity<Compra>(res, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		logger.info("Obteniendo Compra con id: "+id );
		Compra compra = service.getById(id);
		if (compra == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Compra con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Compra>(compra, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarEstadoCompra(@RequestBody Compra pCompra, @PathVariable("id") Integer id){
		Compra compra = service.getById(id);
		if(compra == null) {
			logger.error("Actualizacion Fallida. No existe Compra con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Compra con id " +id), HttpStatus.NOT_FOUND);
		}
		compra.setEstado(pCompra.getEstado());
		service.actualizar(compra);
		return new ResponseEntity<Compra>(compra, HttpStatus.OK);	
	}

}
