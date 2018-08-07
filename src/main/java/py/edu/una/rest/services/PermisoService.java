package py.edu.una.rest.services;

import java.util.Optional;

import py.edu.una.rest.model.Permiso;

public interface PermisoService extends GenericCrudService<Permiso> {
	boolean isExistePermiso(Permiso permiso);
	Optional<Permiso> getByCodigo(String Permiso);
}
