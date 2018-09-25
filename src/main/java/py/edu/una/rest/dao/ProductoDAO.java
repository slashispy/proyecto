package py.edu.una.rest.dao;

import java.util.Optional;

import py.edu.una.rest.model.Producto;

public interface ProductoDAO extends GenericDAO<Producto> {
	Optional<Producto> getByCodigo(String codigo);
}
