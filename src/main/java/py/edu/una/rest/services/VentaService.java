package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.Venta;

public interface VentaService extends GenericCrudService<Venta> {
	public List<Venta> getByEstado(String estado);
}
