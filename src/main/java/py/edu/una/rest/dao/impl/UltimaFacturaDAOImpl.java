package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.UltimaFacturaDAO;
import py.edu.una.rest.model.UltimaFactura;

@Repository("ultimaFacturaDao")
public class UltimaFacturaDAOImpl extends AbstractDAO<UltimaFactura> implements UltimaFacturaDAO {
	
	public UltimaFacturaDAOImpl() {
		super(UltimaFactura.class);
	}

	@Override
	public List<UltimaFactura> listar() {
		return super.listar();
	}

	@Override
	public UltimaFactura getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public UltimaFactura getByUsuario(String usuario) {
		try {
			return (UltimaFactura) em.createNamedQuery("UltimaFactura.findByUsuario")
					.setParameter("usuario", usuario)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

}
