package py.edu.una.rest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.edu.una.rest.model.Compra;
import py.edu.una.rest.services.CompraService;

@CrossOrigin
@RestController
@RequestMapping("compra")
public class CompraControllers {
	
	@Autowired
	private CompraService service;

	public static final Logger logger = LoggerFactory.getLogger(CompraControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarCompras(){
		logger.info("Ejecutando Listar");
		List<Compra> compras = service.listar();
		if (compras.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearCompra(@RequestBody Compra compra){
		try {
			logger.info("Creando el Compra : {}", mapper.writeValueAsString(compra));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Compra res = service.insertar(compra);
		return new ResponseEntity<Compra>(res, HttpStatus.CREATED);
		
	}

}
