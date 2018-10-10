package py.edu.una.rest.dao;

import py.edu.una.rest.model.Caja;

public interface CajaDAO extends GenericDAO<Caja> {
	Caja getCajaAbierta();
}
