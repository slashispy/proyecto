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

import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.PerfilService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("perfil")
public class PerfilControllers {
	
	@Autowired
	private PerfilService service;

	public static final Logger logger = LoggerFactory.getLogger(PerfilControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listar() {
		logger.info("Ejecutando Listar Perfiles");
		List<Perfil> perfiles = service.listar();
		if (perfiles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(perfiles));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Perfil>>(perfiles, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Perfil profile = service.getById(id);
		if (profile == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Perfil con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Perfil>(profile, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearPerfil(@RequestBody Perfil perfil) {
		try {
			logger.info("Insertando Perfil: {}", mapper.writeValueAsString(perfil));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExistePerfil(perfil)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe el perfil"), HttpStatus.CONFLICT);
		} else {
			Perfil usuarioR = service.insertar(perfil);
			return new ResponseEntity<Perfil>(usuarioR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarPerfil(@RequestBody Perfil pactualiza, @PathVariable("id") Integer id){
		Perfil profile = service.getById(id);
		if(profile == null) {
			logger.error("Actualizacion Fallida. No existe Perfil con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe la Perfil con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(pactualiza.getEstado()) || "I".equals(pactualiza.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", pactualiza.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ pactualiza.getEstado()), HttpStatus.CONFLICT);
		}
		profile.setDescripcion(pactualiza.getDescripcion());
		profile.setEstado(pactualiza.getEstado());
		service.actualizar(profile);
		return new ResponseEntity<Perfil>(profile, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarPerfil(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Perfil con id {}", id);
		Perfil profile = service.getById(id);
		if(profile == null) {
			logger.error("Eliminacion Fallida, No existe Perfil con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Perfil con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}
