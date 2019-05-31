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

import py.edu.una.rest.model.MedioPago;
import py.edu.una.rest.services.MedioPagoService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("medio-pago")
public class MedioPagoControllers {
	
	@Autowired
	private MedioPagoService service;

	public static final Logger logger = LoggerFactory.getLogger(MedioPagoControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarMedioPago() {
		logger.info("Ejecutando Listar");
		List<MedioPago> mediosPagos = service.listar();
		if (mediosPagos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(mediosPagos));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<MedioPago>>(mediosPagos, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		MedioPago medioPago = service.getById(id);
		if (medioPago == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Medio de Pago con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<MedioPago>(medioPago, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearMedioPago(@RequestBody MedioPago medioPago) {
		try {
			logger.info("Insertando MedioPago: {}", mapper.writeValueAsString(medioPago));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.existeMedioPago(medioPago)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe Medio de Pago"), HttpStatus.CONFLICT);
		} else {
			MedioPago medioPagoR = service.insertar(medioPago);
			return new ResponseEntity<MedioPago>(medioPagoR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarMedioPago(@RequestBody MedioPago mpactualiza, @PathVariable("id") Integer id){
		MedioPago mp = service.getById(id);
		if(mp == null) {
			logger.error("Actualizacion Fallida. No existe Medio de Pago con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Parametro con id " +id), HttpStatus.NOT_FOUND);
		}
		mp.setDescripcion(mpactualiza.getDescripcion());
		service.actualizar(mp);
		return new ResponseEntity<MedioPago>(mp, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarMedioPago(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Medio de Pago con id {}", id);
		MedioPago mp = service.getById(id);
		if(mp == null) {
			logger.error("Eliminacion Fallida, No existe Medio de Pago con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Medio de Pago con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<MedioPago>(HttpStatus.NO_CONTENT);
	}

}
