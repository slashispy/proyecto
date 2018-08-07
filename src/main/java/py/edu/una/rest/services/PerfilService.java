package py.edu.una.rest.services;

import java.util.List;
import java.util.Optional;

import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Permiso;

public interface PerfilService extends GenericCrudService<Perfil> {
	Optional<Perfil> getByCodigo(String perfil);
	Optional<List<Permiso>> getPermisos(Perfil perfil);
	boolean isExistePerfil(Perfil perfil);
}
