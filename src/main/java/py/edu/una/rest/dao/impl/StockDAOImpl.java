package py.edu.una.rest.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.una.rest.dao.AbstractDAO;
import py.edu.una.rest.dao.StockDAO;
import py.edu.una.rest.model.Stock;

@Repository("stockDao")
public class StockDAOImpl extends AbstractDAO<Stock> implements StockDAO {

	public StockDAOImpl() {
		super(Stock.class);
	}

	@Override
	public List<Stock> listar() {
		return super.listar();
	}

	@Override
	public Stock getById(Integer id) {
		return super.getById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getByControlarStock(String controlarStock) {
		try {
			return em.createNamedQuery("Stock.findByControlarStock")
					.setParameter("controlarStock", controlarStock)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

}
