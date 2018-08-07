package py.edu.una.rest.dao;

import java.util.List;
import java.util.Optional;

import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	Optional<Usuario> getByUsuario(String usuario);
	Optional<List<Perfil>> getPerfiles(Usuario usuario);
}
