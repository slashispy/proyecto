package py.edu.una.rest.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.CompraDAO;
import py.edu.una.rest.model.Compra;

@Repository("compraDao")
public class CompraDAOImpl extends AbstractDAO<Compra> implements CompraDAO {

	public CompraDAOImpl() {
		super(Compra.class);
	}
	
	@Override
	public List<Compra> listar() {
		return super.listar();
	}

	@Override
	public Compra getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Compra insertar(Compra obj) {
		return super.insertar(obj);
	}

	@Override
	public Compra actualizar(Compra obj) {
		return super.insertar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> getByEstado(String estado) {
		try {
			return em.createNamedQuery("Compra.findByEstado")
					.setParameter("estado", estado)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> informe(String estado, Date desde, Date hasta) {
		try {
			return em.createNamedQuery("Compra.informe")
					.setParameter("estado", estado)
					.setParameter("desde", desde)
					.setParameter("hasta", hasta)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
}
