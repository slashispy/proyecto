package py.edu.una.rest.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import py.edu.una.rest.model.Compra;
import py.edu.una.rest.services.CompraService;
import py.edu.una.rest.utils.ErrorDTO;

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
	public ResponseEntity<?> listarCompras(@RequestParam(required = false) String estado){
		logger.info("Ejecutando Listar");
		List<Compra> compras;
		if (estado == null) {
			compras = service.listar();	
		}else {
			compras = service.getByEstado(estado);
		}
		if (compras.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		logger.info("Obteniendo Compra con id: "+id );
		Compra compra = service.getById(id);
		if (compra == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Compra con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Compra>(compra, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarEstadoCompra(@RequestBody Compra pCompra, @PathVariable("id") Integer id){
		Compra compra = service.getById(id);
		if(compra == null) {
			logger.error("Actualizacion Fallida. No existe Compra con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Compra con id " +id), HttpStatus.NOT_FOUND);
		}
		compra.setEstado(pCompra.getEstado());
		service.actualizar(compra);
		return new ResponseEntity<Compra>(compra, HttpStatus.OK);	
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/informe/", method = RequestMethod.GET)
	public ResponseEntity<?> informe(@RequestParam(required = true) String estado, @RequestParam(required = true) String desde, @RequestParam(required = true) String hasta){
		logger.info("Ejecutando Informe");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date desdeD = dateFormat.parse(desde);
			Date hastaD = dateFormat.parse(hasta);
			desdeD = sumarRestarHorasFecha(desdeD, -4);
			hastaD = sumarRestarHorasFecha(hastaD, -4);
			List<Compra> compras = service.informe(estado, desdeD, hastaD);
			if (compras.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
		} catch (ParseException e) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Error en el formato de las fechas"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	private Date sumarRestarHorasFecha(Date fecha, int horas) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, horas); // numero de horas a añadir, o restar en caso de horas<0
		return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	}
}
