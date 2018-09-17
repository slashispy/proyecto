package py.edu.una.rest.controllers;

import java.util.List;
import java.util.Optional;

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

import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.UsuarioService;
import py.edu.una.rest.utils.ErrorDTO;
import py.edu.una.rest.utils.PasswordService;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioControllers {

	@Autowired
	private UsuarioService service;

	public static final Logger logger = LoggerFactory.getLogger(UsuarioControllers.class);

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listar() {
		logger.info("Ejecutando Listar");
		List<Usuario> usuarios = service.listar();
		if (usuarios.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(usuarios));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Usuario user = service.getById(id);
		if (user == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Usuario con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Usuario>(user, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
	public ResponseEntity<?> getByUser(@PathVariable("user") String usuario) {
		Optional<Usuario> user = service.getByUsuario(usuario);
		if (!user.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Usuario con el siguiente user: " + usuario),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Optional<Usuario>>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		try {
			logger.info("Insertando Usuario: {}", mapper.writeValueAsString(usuario));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteUsuario(usuario)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe el usuario"), HttpStatus.CONFLICT);
		} else {
			usuario.setClave(PasswordService.hashPassword(usuario.getClave()));
			Usuario usuarioR = service.insertar(usuario);
			return new ResponseEntity<Usuario>(usuarioR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario uactualiza, @PathVariable("id") Integer id){
		Usuario user = service.getById(id);
		if(user == null) {
			logger.error("Actualizacion Fallida. No existe Usuario con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el Usuario con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(uactualiza.getEstado()) || "I".equals(uactualiza.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", uactualiza.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ uactualiza.getEstado()), HttpStatus.CONFLICT);
		}
		user.setApellido(uactualiza.getApellido());
		user.setNombre(uactualiza.getNombre());
		user.setEmail(uactualiza.getEmail());
		user.setEstado(uactualiza.getEstado());
		service.actualizar(user);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Usuario con id {}", id);
		Usuario user = service.getById(id);
		if(user == null) {
			logger.error("Eliminacion Fallida, No existe Usuario con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Usuario con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}
