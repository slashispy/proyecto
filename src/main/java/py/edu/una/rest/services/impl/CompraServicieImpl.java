package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.CompraDAO;
import py.edu.una.rest.dao.ParametroDAO;
import py.edu.una.rest.model.Compra;
import py.edu.una.rest.model.Parametro;
import py.edu.una.rest.services.CompraService;

@Service("compraService")
@Transactional
public class CompraServicieImpl implements CompraService {

	@Autowired
	private CompraDAO compraDao;
	
	@Autowired
	private ParametroDAO parametrosDao;
	
	@Override
	public List<Compra> listar() {
		return compraDao.listar();
	}

	@Override
	public Compra getById(Integer id) {
		return compraDao.getById(id);
	}

	@Override
	public Compra insertar(Compra obj) {
		Parametro contaParam = parametrosDao.getByClave("Contabilidad");
		if("S".equalsIgnoreCase(contaParam.getValor())) {
			//Do Contabilidad
		}
		return compraDao.insertar(obj);
	}

	@Override
	public Compra actualizar(Compra obj) {
		return compraDao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		compraDao.eliminar(id);

	}

}
