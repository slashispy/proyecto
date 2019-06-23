package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.Venta;

public interface VentaService extends GenericCrudService<Venta> {
	List<Venta> getByEstado(String estado);
	Venta getByFactura(String nroFactura);
}
