package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.Producto;
import py.edu.una.rest.model.Stock;

public interface StockService {
	List<Stock> listar();
	Stock getById(Integer id);
	List<Producto> obtenerStockDisponible();
}
