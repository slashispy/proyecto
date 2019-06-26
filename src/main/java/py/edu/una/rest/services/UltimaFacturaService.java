package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.UltimaFactura;

public interface UltimaFacturaService {
	List<UltimaFactura> listar();
	UltimaFactura getById(Integer id);
	UltimaFactura getByUsuario(String usario);

}
