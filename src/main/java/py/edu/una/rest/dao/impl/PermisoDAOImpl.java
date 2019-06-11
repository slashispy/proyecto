package py.edu.una.rest.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.PermisoDAO;
import py.edu.una.rest.model.Permiso;

@Repository("permisoDao")
public class PermisoDAOImpl extends AbstractDAO<Permiso> implements PermisoDAO {

	public PermisoDAOImpl() {
		super(Permiso.class);
	}

	@Override
	public List<Permiso> listar() {
		return super.listar();
	}

	@Override
	public Permiso getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Permiso insertar(Permiso obj) {
		return super.insertar(obj);
	}

	@Override
	public Permiso actualizar(Permiso obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@Override
	public Optional<Permiso> getByCodigo(String codigo) {
		try {
			return Optional.ofNullable((Permiso) em.createNamedQuery("Permiso.findByCodigo")
					.setParameter("codigo", codigo)
					.getSingleResult());
		}catch(NoResultException e){
			return null;
		}
	}

}
