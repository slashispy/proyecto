package py.edu.una.rest.services;

import java.util.Date;
import java.util.List;

import py.edu.una.rest.model.Compra;

public interface CompraService extends GenericCrudService<Compra> {
	List<Compra> getByEstado(String estado);
	List<Compra> informe(String estado, Date desde, Date hasta);

}
