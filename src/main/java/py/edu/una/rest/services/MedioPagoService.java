package py.edu.una.rest.services;

import py.edu.una.rest.model.MedioPago;

public interface MedioPagoService extends GenericCrudService<MedioPago> {
	MedioPago getByCodigo(String codigo);
	boolean existeMedioPago(MedioPago medioPago);

}
