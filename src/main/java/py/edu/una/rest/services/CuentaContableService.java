package py.edu.una.rest.services;

import py.edu.una.rest.model.CuentaContable;

public interface CuentaContableService extends GenericCrudService<CuentaContable> {
	CuentaContable getByCodigo(String codigo);
	boolean isExisteCuentaContable(CuentaContable cContable);

}
