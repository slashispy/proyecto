package py.edu.una.rest.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.ParametroDAO;
import py.edu.una.rest.dao.VentaDAO;
import py.edu.una.rest.model.DetalleVenta;
import py.edu.una.rest.model.Parametro;
import py.edu.una.rest.model.Venta;
import py.edu.una.rest.services.VentaService;

@Service("ventaService")
@Transactional
public class VentaServiceImpl implements VentaService {

	@Autowired
	private VentaDAO dao;
	
	@Autowired
	private ParametroDAO parametrosDao;
	
	@Override
	public List<Venta> listar() {
		return dao.listar();
	}

	@Override
	public Venta getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Venta insertar(Venta obj) {
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
		obj.setFecha(new Date());
		for(DetalleVenta w: obj.getDetalleVenta()) {
			w.setVenta(obj);
		}
		return dao.insertar(obj);
		
	}

	@Override
	public Venta actualizar(Venta obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public List<Venta> getByEstado(String estado) {
		return dao.getByEstado(estado);
	}

}