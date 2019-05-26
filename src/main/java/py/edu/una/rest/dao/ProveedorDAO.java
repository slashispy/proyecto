package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.Proveedor;

public interface ProveedorDAO extends GenericDAO<Proveedor>{
	Proveedor getByRuc(String ruc);
	List<Proveedor> getByEstado(String estado);
}
