package py.edu.una.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.UsuarioDAO;
import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.UsuarioService;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO dao;

	@Override
	public List<Usuario> listar() {
		return dao.listar();
	}

	@Override
	public Usuario getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Usuario insertar(Usuario obj) {
		return dao.insertar(obj);
	}

	@Override
	public Usuario actualizar(Usuario obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);
	}

	@Override
	public Optional<Usuario> getByUsuario(String usuario) {
		return dao.getByUsuario(usuario);
	}

	@Override
	public boolean isExisteUsuario(Usuario usuario) {
		return dao.getByUsuario(usuario.getUsuario()) == null ? false : true;
	}

	@Override
	public Optional<List<Perfil>> getPerfiles(Usuario usuario) {
		return dao.getPerfiles(usuario);
	}

}
