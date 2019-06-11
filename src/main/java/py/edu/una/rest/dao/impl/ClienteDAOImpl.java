package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.ClienteDAO;
import py.edu.una.rest.model.Cliente;

@Repository("clienteDao")
public class ClienteDAOImpl extends AbstractDAO<Cliente> implements ClienteDAO {

	public ClienteDAOImpl() {
		super(Cliente.class);
	}

	@Override
	public List<Cliente> listar() {
		return super.listar();
	}

	@Override
	public Cliente getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Cliente insertar(Cliente obj) {
		return super.insertar(obj);
	}

	@Override
	public Cliente actualizar(Cliente obj) {
		return super.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}

	@Override
	public Cliente getByRuc(String ruc) {
		try {
			return (Cliente) em.createNamedQuery("Cliente.findByRuc")
					.setParameter("ruc", ruc)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}

}
