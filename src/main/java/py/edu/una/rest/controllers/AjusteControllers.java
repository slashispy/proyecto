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

import py.edu.una.rest.model.Ajuste;
import py.edu.una.rest.services.AjusteService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("ajuste")
public class AjusteControllers {
	
	@Autowired
	private AjusteService service;
	
	public static final Logger logger = LoggerFactory.getLogger(AjusteControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarAjustes(@RequestParam(required = false) String estado){
		logger.info("Ejecutando Listar");
		List<Ajuste> ajustes;
		if (estado == null) {
			ajustes = service.listar();	
		}else {
			ajustes = service.getByEstado(estado);
		}
		if (ajustes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ajuste>>(ajustes, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearAjuste(@RequestBody Ajuste ajuste){
		try {
			logger.info("Creando el Ajuste : {}", mapper.writeValueAsString(ajuste));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Ajuste res = service.insertar(ajuste);
		return new ResponseEntity<Ajuste>(res, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		logger.info("Obteniendo Ajuste con id: "+id );
		Ajuste ajuste = service.getById(id);
		if (ajuste == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Ajuste con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Ajuste>(ajuste, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarEstadoAjuste(@RequestBody Ajuste pAjuste, @PathVariable("id") Integer id){
		Ajuste ajuste = service.getById(id);
		if(ajuste == null) {
			logger.error("Actualizacion Fallida. No existe Ajuste con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Ajuste con id " +id), HttpStatus.NOT_FOUND);
		}
		ajuste.setEstado(pAjuste.getEstado());
		service.actualizar(ajuste);
		return new ResponseEntity<Ajuste>(ajuste, HttpStatus.OK);	
	}

}
