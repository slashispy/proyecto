package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.ProveedorDAO;
import py.edu.una.rest.model.Proveedor;

@Repository("proveedorDao")
public class ProveedorDAOImpl extends AbstractDAO<Proveedor> implements ProveedorDAO {

	public ProveedorDAOImpl() {
		super(Proveedor.class);
	}

	@Override
	public List<Proveedor> listar() {
		return super.listar();
	}

	@Override
	public Proveedor getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Proveedor insertar(Proveedor obj) {
		return super.insertar(obj);
	}

	@Override
	public Proveedor actualizar(Proveedor obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);
	}

	@Override
	public Proveedor getByRuc(String ruc) {
		try {
			return (Proveedor) em.createNamedQuery("Proveedor.findByRuc")
					.setParameter("ruc", ruc)
					.getSingleResult();
		}catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> getByEstado(String estado) {
		try {
			return em.createNamedQuery("Proveedor.findByEstado")
					.setParameter("estado", estado)
					.getResultList();
		}catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
