package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.CuentaContableDAO;
import py.edu.una.rest.model.CuentaContable;

@Repository("cuentaContableDao")
public class CuentaContableDAOImpl extends AbstractDAO<CuentaContable> implements CuentaContableDAO {

	public CuentaContableDAOImpl() {
		super(CuentaContable.class);
	}

	@Override
	public List<CuentaContable> listar() {
		return super.listar();
	}

	@Override
	public CuentaContable getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public CuentaContable insertar(CuentaContable obj) {
		return super.insertar(obj);
	}

	@Override
	public CuentaContable actualizar(CuentaContable obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);
	}

	@Override
	public CuentaContable getByCodigo(String codigo) {
		try {
			return (CuentaContable) em.createNamedQuery("CuentaContable.findByCodigo")
					.setParameter("codigo", codigo)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}

}
