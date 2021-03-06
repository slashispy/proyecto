package py.edu.una.rest.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.VentaDAO;
import py.edu.una.rest.model.Venta;

@Repository("ventaDao")
public class VentaDAOImpl extends AbstractDAO<Venta> implements VentaDAO {

	public VentaDAOImpl() {
		super(Venta.class);
	}

	@Override
	public List<Venta> listar() {
		return super.listar();
	}

	@Override
	public Venta getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Venta insertar(Venta obj) {
		return super.insertar(obj);
	}

	@Override
	public Venta actualizar(Venta obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> getByEstado(String estado) {
		try {
			return em.createNamedQuery("Venta.findByEstado")
					.setParameter("estado", estado)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public Venta getByNroFactura(String nroFactura) {
		try {
			return (Venta) em.createNamedQuery("Venta.findByFactura")
					.setParameter("nroFactura", nroFactura)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> informe(String estado, Date desde, Date hasta) {
		try {
			return em.createNamedQuery("Venta.informe")
					.setParameter("estado", estado)
					.setParameter("desde", desde)
					.setParameter("hasta", hasta)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

}
