package py.edu.una.rest.services;

import py.edu.una.rest.model.Caja;
import py.edu.una.rest.model.Usuario;

public interface CajaService extends GenericCrudService<Caja> {
	Caja getCajaAbierta(String uso, Usuario usuario);
}
