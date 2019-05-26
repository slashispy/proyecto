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

import py.edu.una.rest.model.Proveedor;
import py.edu.una.rest.services.ProveedorService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("proveedor")
public class ProveedorControllers {
	
	@Autowired
	private ProveedorService service;

	public static final Logger logger = LoggerFactory.getLogger(ProveedorControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listar(@RequestParam(required = false) String estado) {
		logger.info("Ejecutando Listar Proveedores");
		List<Proveedor> proveedores;
		if(estado == null) {
			proveedores = service.listar();
		}else {
			proveedores = service.getByEstado(estado);
		}
		if (proveedores.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(proveedores));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Proveedor>>(proveedores, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Proveedor supplier = service.getById(id);
		if (supplier == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Proveedor con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Proveedor>(supplier, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor) {
		try {
			logger.info("Insertando Proveedor: {}", mapper.writeValueAsString(proveedor));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteProveedor(proveedor)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe el proveedor"), HttpStatus.CONFLICT);
		} else {
			Proveedor proveedorR = service.insertar(proveedor);
			return new ResponseEntity<Proveedor>(proveedorR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarProveedor(@RequestBody Proveedor pactualiza, @PathVariable("id") Integer id){
		Proveedor supplier = service.getById(id);
		if(supplier == null) {
			logger.error("Actualizacion Fallida. No existe Proveedor con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe la Proveedor con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(pactualiza.getEstado()) || "I".equals(pactualiza.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", pactualiza.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ pactualiza.getEstado()), HttpStatus.CONFLICT);
		}
		supplier.setRazonSocial(pactualiza.getRazonSocial());
		supplier.setEstado(pactualiza.getEstado());
		supplier.setTimbrado(pactualiza.getTimbrado());
		supplier.setDireccion(pactualiza.getDireccion());
		supplier.setEmail(pactualiza.getEmail());
		supplier.setTelefono(pactualiza.getTelefono());
		service.actualizar(supplier);
		return new ResponseEntity<Proveedor>(supplier, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarProveedor(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Proveedor con id {}", id);
		Proveedor supplier = service.getById(id);
		if(supplier == null) {
			logger.error("Eliminacion Fallida, No existe Proveedor con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Proveedor con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Proveedor>(HttpStatus.NO_CONTENT);
	}

}
