package py.edu.una.rest.dao;

import java.util.Date;
import java.util.List;

import py.edu.una.rest.model.Venta;

public interface VentaDAO extends GenericDAO<Venta> {
	List<Venta> getByEstado(String estado);
	Venta getByNroFactura(String nrofactura);
	List<Venta> informe(String estado, Date desde, Date hasta);
}
