package py.edu.una.rest.dao;

import py.edu.una.rest.model.Parametro;

public interface ParametroDAO extends GenericDAO<Parametro> {
	Parametro getByClave(String clave);

}
