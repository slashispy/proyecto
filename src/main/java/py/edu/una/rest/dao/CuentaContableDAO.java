package py.edu.una.rest.dao;

import py.edu.una.rest.model.CuentaContable;

public interface CuentaContableDAO extends GenericDAO<CuentaContable> {
	CuentaContable getByCodigo(String codigo);
}
