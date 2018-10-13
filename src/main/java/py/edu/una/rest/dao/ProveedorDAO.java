package py.edu.una.rest.dao;

import py.edu.una.rest.model.Proveedor;

public interface ProveedorDAO extends GenericDAO<Proveedor>{
	Proveedor getByRuc(String ruc);
}
