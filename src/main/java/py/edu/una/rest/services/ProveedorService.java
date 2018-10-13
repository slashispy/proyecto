package py.edu.una.rest.services;

import py.edu.una.rest.model.Proveedor;

public interface ProveedorService extends GenericCrudService<Proveedor> {
	Proveedor getByRuc(String ruc);
	boolean isExisteProveedor(Proveedor proveedor);
}
