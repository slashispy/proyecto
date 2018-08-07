package py.edu.una.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.PermisoDAO;
import py.edu.una.rest.model.Permiso;
import py.edu.una.rest.services.PermisoService;

@Service("permisoService")
@Transactional
public class PermisoServiceImpl implements PermisoService {

	@Autowired
	private PermisoDAO dao;
	
	@Override
	public List<Permiso> listar() {
		return dao.listar();
	}

	@Override
	public Permiso getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Permiso insertar(Permiso obj) {
		return dao.insertar(obj);
	}

	@Override
	public Permiso actualizar(Permiso obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);
	}

	@Override
	public boolean isExistePermiso(Permiso permiso) {
		return dao.getByCodigo(permiso.getCodigo()) == null ? false : true;
	}

	@Override
	public Optional<Permiso> getByCodigo(String Permiso) {
		return dao.getByCodigo(Permiso);
	}

}
