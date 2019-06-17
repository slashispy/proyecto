package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.Stock;

public interface StockDAO {
	List<Stock> listar();
	Stock getById(Integer id);
	List<Stock> getByControlarStock(String controlarStock);
}
