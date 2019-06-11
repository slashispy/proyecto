package py.edu.una.rest.services;

import java.util.List;

import py.edu.una.rest.model.Ajuste;

public interface AjusteService extends GenericCrudService<Ajuste> {
	List<Ajuste> getByEstado(String estado);

}
