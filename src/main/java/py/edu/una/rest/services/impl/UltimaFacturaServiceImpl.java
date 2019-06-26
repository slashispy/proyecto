package py.edu.una.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.una.rest.dao.UltimaFacturaDAO;
import py.edu.una.rest.model.UltimaFactura;
import py.edu.una.rest.services.UltimaFacturaService;

@Service("ultimaFacturaService")
public class UltimaFacturaServiceImpl implements UltimaFacturaService {

	@Autowired UltimaFacturaDAO dao;
	
	@Override
	public List<UltimaFactura> listar() {
		return dao.listar();
	}

	@Override
	public UltimaFactura getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public UltimaFactura getByUsuario(String usuario) {
		return dao.getByUsuario(usuario);
	}

}
