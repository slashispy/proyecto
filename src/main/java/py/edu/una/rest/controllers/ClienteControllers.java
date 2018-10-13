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

import py.edu.una.rest.model.Cliente;
import py.edu.una.rest.services.ClienteService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("cliente")
public class ClienteControllers {
	
	@Autowired
	private ClienteService service;

	public static final Logger logger = LoggerFactory.getLogger(ClienteControllers.class);

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarClientes() {
		logger.info("Ejecutando Listar");
		List<Cliente> clientes = service.listar();
		if (clientes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(clientes));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Cliente client = service.getById(id);
		if (client == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Cliente con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Cliente>(client, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
		try {
			logger.info("Insertando Cliente: {}", mapper.writeValueAsString(cliente));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteCliente(cliente)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe el cliente"), HttpStatus.CONFLICT);
		} else {
			Cliente clienteR = service.insertar(cliente);
			return new ResponseEntity<Cliente>(clienteR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarUsuario(@RequestBody Cliente cactualiza, @PathVariable("id") Integer id){
		Cliente cl = service.getById(id);
		if(cl == null) {
			logger.error("Actualizacion Fallida. No existe Cliente con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el Cliente con id " +id), HttpStatus.NOT_FOUND);
		}
		cl.setRazonSocial(cactualiza.getRazonSocial());
		service.actualizar(cl);
		return new ResponseEntity<Cliente>(cl, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarCliente(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Cliente con id {}", id);
		Cliente cl = service.getById(id);
		if(cl == null) {
			logger.error("Eliminacion Fallida, No existe Cliente con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Cliente con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

}
