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

import py.edu.una.rest.model.Producto;
import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.ProductoService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("producto")
public class ProductoControllers {
	
	@Autowired
	private ProductoService service;

	public static final Logger logger = LoggerFactory.getLogger(ProductoControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listar() {
		logger.info("Ejecutando Listar Productos");
		List<Producto> productos = service.listar();
		if (productos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(productos));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Producto product = service.getById(id);
		if (product == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Producto con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Producto>(product, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
		try {
			logger.info("Insertando Producto: {}", mapper.writeValueAsString(producto));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteProducto(producto)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe el producto"), HttpStatus.CONFLICT);
		} else {
			Producto productoR = service.insertar(producto);
			return new ResponseEntity<Producto>(productoR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto pactualiza, @PathVariable("id") Integer id){
		Producto product = service.getById(id);
		if(product == null) {
			logger.error("Actualizacion Fallida. No existe Producto con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe la Producto con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("A".equals(pactualiza.getEstado()) || "I".equals(pactualiza.getEstado()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Estado {}. ", pactualiza.getEstado());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ pactualiza.getEstado()), HttpStatus.CONFLICT);
		}
		product.setDescripcion(pactualiza.getDescripcion());
		product.setEstado(pactualiza.getEstado());
		product.setCantidad(pactualiza.getCantidad());
		product.setCantidadMinima(pactualiza.getCantidadMinima());
		product.setPrecioUnitario(pactualiza.getPrecioUnitario());
		product.setControlarStock(pactualiza.getControlarStock());
		service.actualizar(product);
		return new ResponseEntity<Producto>(product, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarProducto(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Producto con id {}", id);
		Producto product = service.getById(id);
		if(product == null) {
			logger.error("Eliminacion Fallida, No existe Producto con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Producto con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}
