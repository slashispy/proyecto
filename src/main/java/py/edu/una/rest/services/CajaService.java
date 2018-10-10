package py.edu.una.rest.services;

import py.edu.una.rest.model.Caja;

public interface CajaService extends GenericCrudService<Caja> {
	Caja getCajaAbierta();
}
