package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.Caja;
import py.edu.una.rest.model.Usuario;

public interface CajaService extends GenericCrudService<Caja> {
	Caja getCajaAbierta(String uso, Usuario usuario);
	List<Caja> getCajaByUsuario(Usuario usuario);
}
