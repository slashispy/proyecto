package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.AjusteDAO;
import py.edu.una.rest.model.Ajuste;

@Repository("ajusteDao")
public class AjusteDAOImpl extends AbstractDAO<Ajuste> implements AjusteDAO {

	public AjusteDAOImpl() {
		super(Ajuste.class);
	}

	@Override
	public List<Ajuste> listar() {
		return super.listar();
	}

	@Override
	public Ajuste getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Ajuste insertar(Ajuste obj) {
		return super.insertar(obj);
	}

	@Override
	public Ajuste actualizar(Ajuste obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ajuste> getByEstado(String estado) {
		try {
			return em.createNamedQuery("Ajuste.findByEstado")
					.setParameter("estado", estado)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

}
