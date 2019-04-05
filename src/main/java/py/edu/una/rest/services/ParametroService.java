package py.edu.una.rest.services;

import py.edu.una.rest.model.Parametro;

public interface ParametroService extends GenericCrudService<Parametro> {
	Parametro getByClave(String clave);
	boolean isExisteParametro(Parametro parametro);

}
