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

import py.edu.una.rest.model.TipoTransaccion;
import py.edu.una.rest.services.TipoTransaccionService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("tipoTransaccion")
public class TipoTransaccionControllers {
	
	@Autowired
	private TipoTransaccionService service;

	public static final Logger logger = LoggerFactory.getLogger(TipoTransaccionControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarTipoTransacciones(@RequestParam(required = false) String uso) {
		logger.info("Ejecutando Listar");
		List<TipoTransaccion> tipoTransacciones;
		if(uso == null) {
			tipoTransacciones = service.listar();
		}else {
			switch (uso) {
			case "C":
				tipoTransacciones = service.obtenerTransaccionesCompra();
				break;
			case "V":
				tipoTransacciones = service.obtenerTransaccionesVenta();
				break;
			case "A":
				tipoTransacciones = service.obtenerTransaccionesAjustes();
				break;
			default: 
				tipoTransacciones = service.listar();
			}	
		}
		if (tipoTransacciones.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(tipoTransacciones));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<TipoTransaccion>>(tipoTransacciones, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		TipoTransaccion tipoTransaccion = service.getById(id);
		if (tipoTransaccion == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay TipoTransaccion con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<TipoTransaccion>(tipoTransaccion, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearTipoTransaccion(@RequestBody TipoTransaccion tipoTransaccion) {
		try {
			logger.info("Insertando TipoTransaccion: {}", mapper.writeValueAsString(tipoTransaccion));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteTipoTransaccion(tipoTransaccion)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe TipoTransaccion"), HttpStatus.CONFLICT);
		} else {
			TipoTransaccion tipoTransaccionR = service.insertar(tipoTransaccion);
			return new ResponseEntity<TipoTransaccion>(tipoTransaccionR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarTipoTransaccion(@RequestBody TipoTransaccion tpactualiza, @PathVariable("id") Integer id){
		TipoTransaccion tpm = service.getById(id);
		if(tpm == null) {
			logger.error("Actualizacion Fallida. No existe TipoTransaccion con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe TipoTransaccion con id " +id), HttpStatus.NOT_FOUND);
		}
		tpm.setDescripcion(tpactualiza.getDescripcion());
		tpm.setUso(tpactualiza.getUso());
		service.actualizar(tpm);
		return new ResponseEntity<TipoTransaccion>(tpm, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarTipoTransaccion(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando TipoTransaccion con id {}", id);
		TipoTransaccion tpm = service.getById(id);
		if(tpm == null) {
			logger.error("Eliminacion Fallida, No existe TipoTransaccion con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe TipoTransaccion con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<TipoTransaccion>(HttpStatus.NO_CONTENT);
	}
	
	

}
