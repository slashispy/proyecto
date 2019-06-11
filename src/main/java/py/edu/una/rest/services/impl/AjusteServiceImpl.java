package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.AjusteDAO;
import py.edu.una.rest.dao.ParametroDAO;
import py.edu.una.rest.model.Ajuste;
import py.edu.una.rest.model.DetalleAjuste;
import py.edu.una.rest.model.Parametro;
import py.edu.una.rest.services.AjusteService;

@Service("ajusteService")
@Transactional
public class AjusteServiceImpl implements AjusteService {

	@Autowired
	private AjusteDAO dao;
	
	@Autowired
	private ParametroDAO parametrosDao;
	
	@Override
	public List<Ajuste> listar() {
		return dao.listar();
	}

	@Override
	public Ajuste getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Ajuste insertar(Ajuste obj) {
		Parametro contaParam = parametrosDao.getByClave("Contabilidad");
		if(contaParam == null) {
			contaParam = new Parametro();
			contaParam.setClave("Contabilidad");
			contaParam.setValor("N");
		}
		if("S".equalsIgnoreCase(contaParam.getValor())) {
			//Do Contabilidad
		}
		obj.setEstado("A");
		for(DetalleAjuste w: obj.getDetallesAjuste()) {
			w.setAjuste(obj);
		}
		return dao.insertar(obj);
	}

	@Override
	public Ajuste actualizar(Ajuste obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public List<Ajuste> getByEstado(String estado) {
		return dao.getByEstado(estado);
	}

}
