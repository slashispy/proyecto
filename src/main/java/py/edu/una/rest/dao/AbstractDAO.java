package py.edu.una.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDAO<T> implements GenericDAO<T>{

	@PersistenceContext
	protected EntityManager em;
	
	public Class<?> getClazz() {
		return clazz;
	}

	private Class<?> clazz;
	
	public AbstractDAO(Class<?> clz){
		this.clazz = clz;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
		return em.createNamedQuery(clazz.getSimpleName()+".findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Integer id) {
		return (T) em.find(clazz, id);
	}

	@Override
	public T insertar(T obj) {
		em.persist(obj);
		return obj;
	}

	@Override
	public T actualizar(T obj) {
		return em.merge(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void eliminar(Integer id) {
		T obj = (T) em.find(this.clazz, id);
		em.remove(obj);
	}
	
}
