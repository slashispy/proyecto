package py.edu.una.rest.dao;

import java.util.List;

public interface GenericDAO<T> {

	List<T> listar();
	T getById(Integer id);
	T insertar(T obj);
	T actualizar(T obj);
	void eliminar(Integer id);
}
