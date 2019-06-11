package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.Ajuste;

public interface AjusteDAO extends GenericDAO<Ajuste> {
	List<Ajuste> getByEstado(String estado);
}
