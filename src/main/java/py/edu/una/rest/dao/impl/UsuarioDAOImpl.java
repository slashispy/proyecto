package py.edu.una.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.UsuarioDAO;
import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Usuario;

@Repository("usuarioDao")
public class UsuarioDAOImpl extends AbstractDAO<Usuario> implements UsuarioDAO {
	
	
	public UsuarioDAOImpl() {
		super(Usuario.class);
	}

	@Override
	public List<Usuario> listar() {
		return super.listar();
	}

	@Override
	public Usuario getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Usuario insertar(Usuario obj) {
		return super.insertar(obj);
	}

	@Override
	public Usuario actualizar(Usuario obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@Override
	public Optional<Usuario> getByUsuario(String usuario) {
		try {
			return Optional.ofNullable((Usuario) em.createNamedQuery("Usuario.findByUsuario")
					.setParameter("usuario", usuario)
					.getSingleResult());
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Optional<List<Perfil>> getPerfiles(Usuario usuario) {
		Usuario user = super.getById(usuario.getId());
		if(user!= null) {
			return Optional.ofNullable(user.getPerfiles());
		}else{
			return Optional.ofNullable(new ArrayList<Perfil>());
		}
	}

}
