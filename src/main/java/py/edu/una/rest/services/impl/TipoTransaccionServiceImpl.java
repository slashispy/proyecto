package py.edu.una.rest.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.TipoTransaccionDAO;
import py.edu.una.rest.model.TipoTransaccion;
import py.edu.una.rest.services.TipoTransaccionService;

@Service("tipoTransaccionService")
@Transactional
public class TipoTransaccionServiceImpl implements TipoTransaccionService {

	@Autowired
	private TipoTransaccionDAO dao;
	
	@Override
	public List<TipoTransaccion> listar() {
		return dao.listar();
	}

	@Override
	public TipoTransaccion getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public TipoTransaccion insertar(TipoTransaccion obj) {
		return dao.insertar(obj);
	}

	@Override
	public TipoTransaccion actualizar(TipoTransaccion obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public List<TipoTransaccion> obtenerTransaccionesCompra() {
		List<TipoTransaccion> listaCompra = new ArrayList<>();
		List<TipoTransaccion> temporal = dao.getFilterByUso("C");
		if(temporal!=null) {
			listaCompra.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-V");
		if(temporal!=null) {
			listaCompra.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-A");
		if(temporal!=null) {
			listaCompra.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-V-A");
		if(temporal!=null) {
			listaCompra.addAll(temporal);
		}
		if(listaCompra.isEmpty()) {
			return null;
		}
		return listaCompra;
	}

	@Override
	public List<TipoTransaccion> obtenerTransaccionesVenta() {
		List<TipoTransaccion> listaVenta = new ArrayList<>();
		List<TipoTransaccion> temporal = dao.getFilterByUso("V");
		if(temporal!=null) {
			listaVenta.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-V");
		if(temporal!=null) {
			listaVenta.addAll(temporal);
		}
		temporal = dao.getFilterByUso("V-A");
		if(temporal!=null) {
			listaVenta.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-V-A");
		if(temporal!=null) {
			listaVenta.addAll(temporal);
		}
		if(listaVenta.isEmpty()) {
			return null;
		}
		return listaVenta;
	}

	@Override
	public List<TipoTransaccion> obtenerTransaccionesAjustes() {
		List<TipoTransaccion> listaAjuste = new ArrayList<>();
		List<TipoTransaccion> temporal = dao.getFilterByUso("A");
		if(temporal!=null) {
			listaAjuste.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-A");
		if(temporal!=null) {
			listaAjuste.addAll(temporal);
		}
		temporal = dao.getFilterByUso("V-A");
		if(temporal!=null) {
			listaAjuste.addAll(temporal);
		}
		temporal = dao.getFilterByUso("C-V-A");
		if(temporal!=null) {
			listaAjuste.addAll(temporal);
		}
		if(listaAjuste.isEmpty()) {
			return null;
		}
		return listaAjuste;
	}

	@Override
	public boolean isExisteTipoTransaccion(TipoTransaccion tipoTransaccion) {
		return dao.getByCodigo(tipoTransaccion.getCodigo()) == null ? false : true;
	}

}
