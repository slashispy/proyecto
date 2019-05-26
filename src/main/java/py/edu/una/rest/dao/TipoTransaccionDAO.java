package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.TipoTransaccion;

public interface TipoTransaccionDAO extends GenericDAO<TipoTransaccion> {
	List<TipoTransaccion> getFilterByUso(String uso);
}
