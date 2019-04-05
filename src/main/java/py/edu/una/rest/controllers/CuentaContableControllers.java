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

import py.edu.una.rest.model.CuentaContable;
import py.edu.una.rest.services.CuentaContableService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("cuenta-contable")
public class CuentaContableControllers {
	
	@Autowired
	private CuentaContableService service;

	public static final Logger logger = LoggerFactory.getLogger(CuentaContableControllers.class);

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarCuentasContables() {
		logger.info("Ejecutando Listar");
		List<CuentaContable> cuentas = service.listar();
		if (cuentas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			try {
				logger.info("Se obtuvo de la base de datos: " + mapper.writeValueAsString(cuentas));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<List<CuentaContable>>(cuentas, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		CuentaContable account = service.getById(id);
		if (account == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Cuenta Contable con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<CuentaContable>(account, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> crearCuentaContable(@RequestBody CuentaContable cuenta) {
		try {
			logger.info("Insertando Cuenta Contable: {}", mapper.writeValueAsString(cuenta));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (service.isExisteCuentaContable(cuenta)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("ya existe la cuenta contable"), HttpStatus.CONFLICT);
		} else {
			CuentaContable cuentaR = service.insertar(cuenta);
			return new ResponseEntity<CuentaContable>(cuentaR, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarCuentaContable(@RequestBody CuentaContable cactualiza, @PathVariable("id") Integer id){
		CuentaContable cl = service.getById(id);
		if(cl == null) {
			logger.error("Actualizacion Fallida. No existe Cuenta Contable con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Cuenta Contable con id " +id), HttpStatus.NOT_FOUND);
		}
		if(!("D".equals(cactualiza.getTipoCuenta()) || "H".equals(cactualiza.getTipoCuenta()))) {
			logger.error("Actualizacion Fallida. Valor no valido para el campo Tipo Cuenta {}. ", cactualiza.getTipoCuenta());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. Valor no valido para el campo Estado {}. "+ cactualiza.getTipoCuenta()), HttpStatus.CONFLICT);
		}
		cl.setDescripcion(cactualiza.getDescripcion());
		cl.setTipoCuenta(cactualiza.getTipoCuenta());
		cl.setNumeroCuenta(cactualiza.getNumeroCuenta());
		cl.setCuentaPadre(cactualiza.getCuentaPadre());
		cl.setAsentable(cactualiza.getAsentable());
		service.actualizar(cl);
		return new ResponseEntity<CuentaContable>(cl, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarCuentaContable(@PathVariable("id") Integer id){
		logger.info("Obteniendo y eliminando Cuenta Contable con id {}", id);
		CuentaContable cl = service.getById(id);
		if(cl == null) {
			logger.error("Eliminacion Fallida, No existe Cuenta Contable con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Cuenta Contable con el id "+ id), HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<CuentaContable>(HttpStatus.NO_CONTENT);
	}

}
