package py.edu.una.rest.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.edu.una.rest.model.Caja;
import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.CajaService;
import py.edu.una.rest.services.UsuarioService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("caja")
public class CajaControllers {

	@Autowired
	private CajaService service;
	
	@Autowired
	private UsuarioService usuarioService;

	public static final Logger logger = LoggerFactory.getLogger(CajaControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCajaAbierta(@RequestParam String uso, @RequestParam String usuario){
		Optional<Usuario> user = usuarioService.getByUsuario(usuario);
		if(!user.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Usuario no valido"), HttpStatus.NOT_FOUND);
		}
		logger.info("Obteniendo Caja Abierta: ");
		Caja cajaA = service.getCajaAbierta(uso, user.get());
		if(cajaA == null) {
			logger.error("No hay Cajas Abiertas.");
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Cajas Abiertas."), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Caja>(cajaA, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> registrarCaja(@RequestBody Caja caja) {
		try {
			logger.info("Insertando Caja: {}", mapper.writeValueAsString(caja));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Caja cajaR = service.insertar(caja);
		return new ResponseEntity<Caja>(cajaR, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> cerrarCaja(@RequestBody Caja cactualiza, @PathVariable("id") Integer id){
		Caja caja = service.getById(id);
		if(caja == null) {
			logger.error("Cierre Fallida. No existe Caja con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Cierre Fallida. No existe la caja con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!"C".equals(cactualiza.getEstadoCaja()) ) {
			logger.error("Cierre Fallida. Valor no valido para el campo Estado {}. ", cactualiza.getEstadoCaja());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Cierre Fallida. Valor no valido para el campo Estado {}. "+ cactualiza.getEstadoCaja()), HttpStatus.CONFLICT);
		}
		if(cactualiza.getFechaCierre() == null) {
			logger.error("Cierre Fallida. fechaCierre no puede ser nulo {}. ", cactualiza);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Cierre Fallida. fechaCierre no puede ser nulo"), HttpStatus.CONFLICT);
		}
		if(cactualiza.getMontoCierre() == null) {
			logger.error("Cierre Fallida. MontoCierre no puede ser nulo {}. ", cactualiza);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Cierre Fallida. MontoCierre no puede ser nulo"), HttpStatus.CONFLICT);
		}
		caja.setFechaCierre(cactualiza.getFechaCierre());
		caja.setMontoCierre(cactualiza.getMontoCierre());
		caja.setEstadoCaja(cactualiza.getEstadoCaja());
		service.actualizar(caja);
		return new ResponseEntity<Caja>(caja, HttpStatus.OK);	
	}

}
