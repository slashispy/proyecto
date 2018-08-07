package py.edu.una.rest.services;

import java.util.List;

public interface GenericCrudService<T> {
	List<T> listar();
	T getById(Integer id);
	T insertar(T obj);
	T actualizar(T obj);
	void eliminar(Integer id);
}
