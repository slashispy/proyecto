package py.edu.una.rest.dao;

import py.edu.una.rest.model.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente> {
	Cliente getByRuc(String ruc);
}
