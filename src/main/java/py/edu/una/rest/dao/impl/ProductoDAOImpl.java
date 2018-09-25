package py.edu.una.rest.dao.impl;

import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.ProductoDAO;
import py.edu.una.rest.model.Producto;

@Repository("productoDao")
public class ProductoDAOImpl extends AbstractDAO<Producto> implements ProductoDAO {

	public ProductoDAOImpl() {
		super(Producto.class);
	}

	@Override
	public Optional<Producto> getByCodigo(String codigo) {
		try {
			return Optional.ofNullable((Producto) em.createNamedQuery("Producto.findByCodigo")
					.setParameter("codigo", codigo)
					.getSingleResult());
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}

}
