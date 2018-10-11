package py.edu.una.rest.services;

import py.edu.una.rest.model.Cliente;

public interface ClienteService extends GenericCrudService<Cliente> {
	Cliente getByRuc(String ruc);
	boolean isExisteCliente(Cliente cl);

}
