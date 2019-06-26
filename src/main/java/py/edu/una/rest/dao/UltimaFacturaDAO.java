package py.edu.una.rest.dao;

import java.util.List;

import py.edu.una.rest.model.UltimaFactura;

public interface UltimaFacturaDAO {
	List<UltimaFactura> listar();
	UltimaFactura getById(Integer id);
	UltimaFactura getByUsuario(String usario);
}
