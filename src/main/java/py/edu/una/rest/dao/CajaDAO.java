package py.edu.una.rest.dao;

import py.edu.una.rest.model.Caja;
import py.edu.una.rest.model.Usuario;

public interface CajaDAO extends GenericDAO<Caja> {
	Caja getCajaAbierta(String uso, Usuario usuario);
}
