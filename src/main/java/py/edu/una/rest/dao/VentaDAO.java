package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.Venta;

public interface VentaDAO extends GenericDAO<Venta> {
	List<Venta> getByEstado(String estado);
}
