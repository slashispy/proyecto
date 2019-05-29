package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.TipoTransaccionDAO;
import py.edu.una.rest.model.TipoTransaccion;

@Repository("tipoTransaccionDao")
public class TipoTransaccionDAOImpl extends AbstractDAO<TipoTransaccion> implements TipoTransaccionDAO {
	
	public TipoTransaccionDAOImpl() {
		super(TipoTransaccion.class);
	}

	@Override
	public List<TipoTransaccion> listar() {
		return super.listar();
	}

	@Override
	public TipoTransaccion getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public TipoTransaccion insertar(TipoTransaccion obj) {
		return super.insertar(obj);
	}

	@Override
	public TipoTransaccion actualizar(TipoTransaccion obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTransaccion> getFilterByUso(String uso) {
		try {
			return em.createNamedQuery("TipoTransaccion.filterByUso")
					.setParameter("uso", uso)
					.getResultList();
		}catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TipoTransaccion getByCodigo(String codigo) {
		try {
			return (TipoTransaccion) em.createNamedQuery("TipoTransaccion.findByCodigo")
					.setParameter("codigo", codigo)
					.getSingleResult();
		}catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
