package py.edu.una.rest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.CompraDAO;
import py.edu.una.rest.model.Compra;

@Repository("compraDao")
public class CompraDAOImpl extends AbstractDAO<Compra> implements CompraDAO {

	public CompraDAOImpl() {
		super(Compra.class);
	}
	
	@Override
	public List<Compra> listar() {
		return super.listar();
	}

	@Override
	public Compra getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Compra insertar(Compra obj) {
		return super.insertar(obj);
	}

	@Override
	public Compra actualizar(Compra obj) {
		return super.insertar(obj);
	}

	@Override
	public void eliminar(Integer id) {
		super.eliminar(id);

	}
}
