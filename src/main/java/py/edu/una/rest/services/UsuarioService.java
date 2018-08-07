package py.edu.una.rest.services;

import java.util.List;
import java.util.Optional;

import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Usuario;

public interface UsuarioService extends GenericCrudService<Usuario> {
	Optional<Usuario> getByUsuario(String usuario);
	Optional<List<Perfil>> getPerfiles(Usuario usuario);
	boolean isExisteUsuario(Usuario usuario);
}
