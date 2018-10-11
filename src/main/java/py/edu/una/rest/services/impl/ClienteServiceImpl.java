package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.ClienteDAO;
import py.edu.una.rest.model.Cliente;
import py.edu.una.rest.services.ClienteService;

@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDAO dao;
	
	@Override
	public List<Cliente> listar() {
		return dao.listar();
	}

	@Override
	public Cliente getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Cliente insertar(Cliente obj) {
		return dao.insertar(obj);
	}

	@Override
	public Cliente actualizar(Cliente obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);
	}

	@Override
	public Cliente getByRuc(String ruc) {
		return dao.getByRuc(ruc);
	}

	@Override
	public boolean isExisteCliente(Cliente cl) {
		return dao.getByRuc(cl.getRuc()) == null ? false : true;
	}

}
