package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.CajaDAO;
import py.edu.una.rest.model.Caja;
import py.edu.una.rest.model.Usuario;

@Repository("cajaDao")
public class CajaDAOImpl extends AbstractDAO<Caja> implements CajaDAO {

	public CajaDAOImpl() {
		super(Caja.class);
	}
	
	@Override
	public List<Caja> listar() {
		return super.listar();
	}

	@Override
	public Caja getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Caja insertar(Caja obj) {
		return super.insertar(obj);
	}

	@Override
	public Caja actualizar(Caja obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@Override
	public Caja getCajaAbierta(String uso, Usuario usuario) {
		try {
			return (Caja)em.createNamedQuery("Caja.cajaAbierta")
					.setParameter("uso", uso)
					.setParameter("usuario", usuario)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}

}
