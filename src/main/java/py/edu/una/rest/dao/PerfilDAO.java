package py.edu.una.rest.dao;

import java.util.List;
import java.util.Optional;

import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Permiso;

public interface PerfilDAO extends GenericDAO<Perfil> {
	Optional<Perfil> getByCodigo(String codigo);
	Optional<List<Permiso>> getPermisos(Perfil perfil);
}
