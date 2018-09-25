package py.edu.una.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.una.rest.dao.ProductoDAO;
import py.edu.una.rest.model.Producto;
import py.edu.una.rest.services.ProductoService;

@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoDAO dao;

	@Override
	public List<Producto> listar() {
		return dao.listar();
	}

	@Override
	public Producto getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Producto insertar(Producto obj) {
		return dao.insertar(obj);
	}

	@Override
	public Producto actualizar(Producto obj) {
		return dao.actualizar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		dao.eliminar(id);

	}

	@Override
	public Optional<Producto> getByCodigo(String codigo) {
		return dao.getByCodigo(codigo);
	}

	@Override
	public boolean isExisteProducto(Producto prod) {
		return dao.getByCodigo(prod.getCodigo()) == null ? false : true;
	}

}
