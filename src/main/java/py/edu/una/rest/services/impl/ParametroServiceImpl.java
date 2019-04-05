package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.ParametroDAO;
import py.edu.una.rest.model.Parametro;
import py.edu.una.rest.services.ParametroService;

@Service("parametroService")
@Transactional
public class ParametroServiceImpl implements ParametroService {
	
	@Autowired
	private ParametroDAO dao;

	@Override
	public List<Parametro> listar() {
		return dao.listar();
	}

	@Override
	public Parametro getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Parametro insertar(Parametro obj) {
		return dao.insertar(obj);
	}

	@Override
	public Parametro actualizar(Parametro obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public Parametro getByClave(String clave) {
		return dao.getByClave(clave);
	}

	@Override
	public boolean isExisteParametro(Parametro parametro) {
		return dao.getByClave(parametro.getClave()) == null ? false : true;
	}

}
