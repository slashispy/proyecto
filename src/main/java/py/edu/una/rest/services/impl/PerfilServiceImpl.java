package py.edu.una.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.PerfilDAO;
import py.edu.una.rest.model.Perfil;
import py.edu.una.rest.model.Permiso;
import py.edu.una.rest.services.PerfilService;

@Service("perfilService")
@Transactional
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDAO dao;
	
	@Override
	public List<Perfil> listar() {
		return dao.listar();
	}

	@Override
	public Perfil getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Perfil insertar(Perfil obj) {
		return dao.insertar(obj);
	}

	@Override
	public Perfil actualizar(Perfil obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public Optional<Perfil> getByCodigo(String codigo) {
		return dao.getByCodigo(codigo);
	}

	@Override
	public Optional<List<Permiso>> getPermisos(Perfil perfil) {
		return dao.getPermisos(perfil);
	}

	@Override
	public boolean isExistePerfil(Perfil perfil) {
		return dao.getByCodigo(perfil.getCodigo()) == null ? false : true;
	}

}
