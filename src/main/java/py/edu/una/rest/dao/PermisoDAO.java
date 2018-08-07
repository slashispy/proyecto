package py.edu.una.rest.dao;

import java.util.Optional;

import py.edu.una.rest.model.Permiso;

public interface PermisoDAO extends GenericDAO<Permiso> {
	Optional<Permiso> getByCodigo(String codigo);

}
