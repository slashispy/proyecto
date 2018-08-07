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

import py.edu.una.rest.model.Permiso;
import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.PermisoService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("permiso")
public class PermisoControllers {
	
	@Autowired
	private PermisoService service;

	public static final Logger logger = LoggerFactory.getLogger(PermisoControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listar() {
		logger.info("Ejecutando Listar Permisos");
		List<Permiso> permisos = service.listar();
		if (permisos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(permisos));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Permiso>>(permisos, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Permiso permission = service.getById(id);
		if (permission == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Permiso con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Permiso>(permission, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearPermiso(@RequestBody Permiso permiso) {
		try {
			logger.info("Insertando Permiso: {}", mapper.writeValueAsString(permiso));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExistePermiso(permiso)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe el Permiso"), HttpStatus.CONFLICT);
		} else {
			Permiso permisoR = service.insertar(permiso);
			return new ResponseEntity<Permiso>(permisoR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarPermiso(@RequestBody Permiso pactualiza, @PathVariable("id") Integer id){
		Permiso permission = service.getById(id);
		if(permission == null) {
			logger.error("Actualizacion Fallida. No existe Permiso con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe la Permiso con id " +id), HttpStatus.NOT_FOUND);
		}
		permission.setDescripcion(pactualiza.getDescripcion());
		service.actualizar(permission);
		return new ResponseEntity<Permiso>(permission, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarPermiso(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Permiso con id {}", id);
		Permiso permission = service.getById(id);
		if(permission == null) {
			logger.error("Eliminacion Fallida, No existe Perfil con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Permiso con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}
