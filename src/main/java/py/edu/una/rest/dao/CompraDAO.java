package py.edu.una.rest.dao;

import java.util.Date;
import java.util.List;

import py.edu.una.rest.model.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
	List<Compra> getByEstado(String estado);
	List<Compra> informe(String estado, Date desde, Date hasta);
}
