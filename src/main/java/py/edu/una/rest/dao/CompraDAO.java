package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
	List<Compra> getByEstado(String estado);
}
