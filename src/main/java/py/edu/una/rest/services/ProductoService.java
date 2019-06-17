package py.edu.una.rest.services;

import java.util.List;
import java.util.Optional;

import py.edu.una.rest.model.Producto;

public interface ProductoService extends GenericCrudService<Producto> {
	Optional<Producto> getByCodigo(String codigo);
	boolean isExisteProducto(Producto prod);
	List<Producto> getByEstado(String estado);
	List<Producto> getByControlarStock(String controlarStock);

}
