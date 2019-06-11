package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.ParametroDAO;
import py.edu.una.rest.model.Parametro;

@Repository("parametroDao")
public class ParametroDAOImpl extends AbstractDAO<Parametro> implements ParametroDAO {

	public ParametroDAOImpl() {
		super(Parametro.class);
	}
	
	@Override
	public List<Parametro> listar() {
		return super.listar();
	}

	@Override
	public Parametro getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Parametro insertar(Parametro obj) {
		return super.insertar(obj);
	}

	@Override
	public Parametro actualizar(Parametro obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@Override
	public Parametro getByClave(String clave) {
		try {
			return (Parametro) em.createNamedQuery("Parametro.findByClave")
					.setParameter("clave", clave)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

}
