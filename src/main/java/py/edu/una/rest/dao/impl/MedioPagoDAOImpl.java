package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.MedioPagoDAO;
import py.edu.una.rest.model.MedioPago;

@Repository("medioPagoDao")
public class MedioPagoDAOImpl extends AbstractDAO<MedioPago> implements MedioPagoDAO {

	public MedioPagoDAOImpl() {
		super(MedioPago.class);
	}

	@Override
	public List<MedioPago> listar() {
		return super.listar();
	}

	@Override
	public MedioPago getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public MedioPago insertar(MedioPago obj) {
		return super.insertar(obj);
	}

	@Override
	public MedioPago actualizar(MedioPago obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@Override
	public MedioPago getByCodigo(String codigo) {
		try {
			return (MedioPago) em.createNamedQuery("MedioPago.findByCodigo")
					.setParameter("codigo", codigo)
					.getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}

}
