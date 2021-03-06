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

import py.edu.una.rest.model.Venta;
import py.edu.una.rest.services.VentaService;
import py.edu.una.rest.utils.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("venta")
public class VentaControllers {
	
	@Autowired
	private VentaService service;
	
	public static final Logger logger = LoggerFactory.getLogger(VentaControllers.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarVentas(@RequestParam(required = false) String estado){
		logger.info("Ejecutando Listar");
		List<Venta> ventas;
		if (estado == null) {
			ventas = service.listar();	
		}else {
			ventas = service.getByEstado(estado);
		}
		if (ventas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearVenta(@RequestBody Venta venta){
		try {
			logger.info("Creando el Venta : {}", mapper.writeValueAsString(venta));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Venta res = service.insertar(venta);
		return new ResponseEntity<Venta>(res, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		logger.info("Obteniendo Venta con id: "+id );
		Venta venta = service.getById(id);
		if (venta == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Venta con el siguiente ID: " + id),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Venta>(venta, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/nroFactura", method = RequestMethod.GET)
	public ResponseEntity<?> getByNroFactura(@RequestParam String nroFactura) {
		logger.info("Obteniendo Venta con nroFactura: "+nroFactura );
		Venta venta = service.getByFactura(nroFactura);
		if (venta == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No hay Venta con el siguiente nroFacura: " + nroFactura),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Venta>(venta, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarEstadoVenta(@RequestBody Venta pVenta, @PathVariable("id") Integer id){
		Venta venta = service.getById(id);
		if(venta == null) {
			logger.error("Actualizacion Fallida. No existe Venta con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe Venta con id " +id), HttpStatus.NOT_FOUND);
		}
		venta.setEstado(pVenta.getEstado());
		service.actualizar(venta);
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);	
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
			List<Venta> ventas = service.informe(estado, desdeD, hastaD);
			if (ventas.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
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
