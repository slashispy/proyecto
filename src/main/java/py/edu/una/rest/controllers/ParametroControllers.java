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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.edu.una.rest.model.Parametro;
import py.edu.una.rest.services.ParametroService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("parametro")
public class ParametroControllers {
	
	@Autowired
	private ParametroService service;

	public static final Logger logger = LoggerFactory.getLogger(ParametroControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarParametros() {
		logger.info("Ejecutando Listar");
		List<Parametro> parametros = service.listar();
		if (parametros.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(parametros));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Parametro>>(parametros, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Parametro parameter = service.getById(id);
		if (parameter == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Parametro con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Parametro>(parameter, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearParametro(@RequestBody Parametro parametro) {
		try {
			logger.info("Insertando Parametro: {}", mapper.writeValueAsString(parametro));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteParametro(parametro)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe Parametro"), HttpStatus.CONFLICT);
		} else {
			Parametro parametroR = service.insertar(parametro);
			return new ResponseEntity<Parametro>(parametroR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarParametro(@RequestBody Parametro pactualiza, @PathVariable("id") Integer id){
		Parametro pm = service.getById(id);
		if(pm == null) {
			logger.error("Actualizacion Fallida. No existe Parametro con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Parametro con id " +id), HttpStatus.NOT_FOUND);
		}
		pm.setValor(pactualiza.getValor());
		service.actualizar(pm);
		return new ResponseEntity<Parametro>(pm, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarParametro(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Parametro con id {}", id);
		Parametro pm = service.getById(id);
		if(pm == null) {
			logger.error("Eliminacion Fallida, No existe Parametro con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Parametro con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Parametro>(HttpStatus.NO_CONTENT);
	}

}
