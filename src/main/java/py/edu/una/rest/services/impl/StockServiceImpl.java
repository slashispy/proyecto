package py.edu.una.rest.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.una.rest.dao.StockDAO;
import py.edu.una.rest.model.Producto;
import py.edu.una.rest.model.Stock;
import py.edu.una.rest.services.ProductoService;
import py.edu.una.rest.services.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockDAO dao;
	
	@Autowired
	private ProductoService productoService;
	
	@Override
	public List<Stock> listar() {
		return dao.listar();
	}

	@Override
	public Stock getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Producto> obtenerStockDisponible() {
		List<Producto> stock = productoService.getByControlarStock("N");
		List<Stock> stockDisponible = dao.getByControlarStock("S");
		if(stockDisponible.isEmpty()) {
			return stock;
		} else {
			Producto productoStock;
			for (Stock w: stockDisponible) {
				if (w.getExistencias().compareTo(new BigDecimal("0")) == 1 ) {
					productoStock = productoService.getById(w.getIdProducto());
					stock.add(productoStock);
				}
			}
		}
		return stock;
	}

}
