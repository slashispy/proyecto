package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.Caja;
import py.edu.una.rest.model.Usuario;

public interface CajaDAO extends GenericDAO<Caja> {
	Caja getCajaAbierta(String uso, Usuario usuario);
	List<Caja> getCajaByUsuario(Usuario usuario);
}
