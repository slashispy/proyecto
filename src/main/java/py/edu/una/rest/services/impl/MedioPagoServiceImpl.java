package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.MedioPagoDAO;
import py.edu.una.rest.model.MedioPago;
import py.edu.una.rest.services.MedioPagoService;

@Service("medioPagoService")
@Transactional
public class MedioPagoServiceImpl implements MedioPagoService {

	@Autowired
	private MedioPagoDAO dao;
	
	@Override
	public List<MedioPago> listar() {
		return dao.listar();
	}

	@Override
	public MedioPago getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public MedioPago insertar(MedioPago obj) {
		return dao.insertar(obj);
	}

	@Override
	public MedioPago actualizar(MedioPago obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);
	}

	@Override
	public MedioPago getByCodigo(String codigo) {
		return dao.getByCodigo(codigo);
	}

	@Override
	public boolean existeMedioPago(MedioPago medioPago) {
		return dao.getByCodigo(medioPago.getCodigo()) == null ? false : true;
	}

}
