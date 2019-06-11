package py.edu.una.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.PerfilDAO;
import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Permiso;

@Repository("perfilDao")
public class PerfilDAOImpl extends AbstractDAO<Perfil> implements PerfilDAO {

	public PerfilDAOImpl() {
		super(Perfil.class);
	}

	@Override
	public List<Perfil> listar() {
		return super.listar();
	}

	@Override
	public Perfil getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Perfil insertar(Perfil obj) {
		return super.insertar(obj);
	}

	@Override
	public Perfil actualizar(Perfil obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);
	}

	@Override
	public Optional<Perfil> getByCodigo(String codigo) {
		try {
			return Optional.ofNullable((Perfil) em.createNamedQuery("Perfil.findByCodigo")
					.setParameter("codigo", codigo)
					.getSingleResult());
		}catch(NoResultException e){
			return null;
		}
	}

	@Override
	public Optional<List<Permiso>> getPermisos(Perfil perfil) {
		Perfil profile = super.getById(perfil.getId());
		if(profile!= null) {
			return Optional.ofNullable(profile.getPermisos());
		}else{
			return Optional.ofNullable(new ArrayList<Permiso>());
		}
	}

}
