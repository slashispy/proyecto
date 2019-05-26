package py.edu.una.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tipo_transacciones")
@NamedQueries({
	@NamedQuery(name="TipoTransaccion.findAll", query="SELECT t FROM TipoTransaccion t"),
	@NamedQuery(name="TipoTransaccion.findByCodigo", query="SELECT t FROM TipoTransaccion t WHERE t.codigo = :codigo"),
	@NamedQuery(name="TipoTransaccion.filterByUso", query="SELECT t FROM TipoTransaccion t WHERE t.uso = :uso")
	})
public class TipoTransaccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String codigo;
	
	private String descripcion;
	
	private String uso;
	
	public TipoTransaccion() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}
	
	
	
	
	

}
