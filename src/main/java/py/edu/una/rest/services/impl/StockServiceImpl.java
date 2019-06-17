package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.una.rest.dao.StockDAO;
import py.edu.una.rest.model.Stock;
import py.edu.una.rest.services.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockDAO dao;
	
	@Override
	public List<Stock> listar() {
		return dao.listar();
	}

	@Override
	public Stock getById(Integer id) {
		return dao.getById(id);
	}

}
