package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.TipoTransaccion;

public interface TipoTransaccionService extends GenericCrudService<TipoTransaccion> {
	List<TipoTransaccion> obtenerTransaccionesCompra();
	List<TipoTransaccion> obtenerTransaccionesVenta();
	List<TipoTransaccion> obtenerTransaccionesAjustes();

}
