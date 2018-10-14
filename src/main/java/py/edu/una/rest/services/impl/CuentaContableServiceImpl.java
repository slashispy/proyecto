package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.CuentaContableDAO;
import py.edu.una.rest.model.CuentaContable;
import py.edu.una.rest.services.CuentaContableService;

@Service("cuentaContableService")
@Transactional
public class CuentaContableServiceImpl implements CuentaContableService {

	@Autowired
	private CuentaContableDAO dao;
	
	@Override
	public List<CuentaContable> listar() {
		return dao.listar();
	}

	@Override
	public CuentaContable getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public CuentaContable insertar(CuentaContable obj) {
		return dao.insertar(obj);
	}

	@Override
	public CuentaContable actualizar(CuentaContable obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);
	}

	@Override
	public CuentaContable getByCodigo(String codigo) {
		return dao.getByCodigo(codigo);
	}

	@Override
	public boolean isExisteCuentaContable(CuentaContable cContable) {
		return dao.getByCodigo(cContable.getCodigo()) == null ? false : true;
	}

}
