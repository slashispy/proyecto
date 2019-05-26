package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.ProveedorDAO;
import py.edu.una.rest.model.Proveedor;
import py.edu.una.rest.services.ProveedorService;

@Service("proveedorService")
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorDAO dao;
	
	@Override  
	public List<Proveedor> listar() {
		return dao.listar();
	}

	@Override
	public Proveedor getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Proveedor insertar(Proveedor obj) {
		return dao.insertar(obj);
	}

	@Override
	public Proveedor actualizar(Proveedor obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);
	}

	@Override
	public Proveedor getByRuc(String ruc) {
		return dao.getByRuc(ruc);
	}

	@Override
	public boolean isExisteProveedor(Proveedor proveedor) {
		return dao.getByRuc(proveedor.getRuc()) == null ? false : true ;
	}

	@Override
	public List<Proveedor> getByEstado(String estado) {
		return dao.getByEstado(estado);
	}

}
