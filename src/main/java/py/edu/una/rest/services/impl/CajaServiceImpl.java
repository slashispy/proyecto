package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.CajaDAO;
import py.edu.una.rest.model.Caja;
import py.edu.una.rest.services.CajaService;

@Service("cajaService")
@Transactional
public class CajaServiceImpl implements CajaService {

	@Autowired
	private CajaDAO dao;
	
	@Override
	public List<Caja> listar() {
		return dao.listar();
	}

	@Override
	public Caja getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Caja insertar(Caja obj) {
		return dao.insertar(obj);
	}

	@Override
	public Caja actualizar(Caja obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public Caja getCajaAbierta() {
		return dao.getCajaAbierta();
	}

}
