package py.edu.una.rest.dao;

import py.edu.una.rest.model.MedioPago;

public interface MedioPagoDAO extends GenericDAO<MedioPago> {
	MedioPago getByCodigo(String codigo);

}
