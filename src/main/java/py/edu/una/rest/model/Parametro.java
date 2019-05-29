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
@Table(name="parametros")
@NamedQueries({
	@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p"),
	@NamedQuery(name="Parametro.findByClave", query="SELECT p FROM Parametro p WHERE p.clave = :clave")
	})
public class Parametro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String clave;
	
	private String valor;
	
//	@ManyToOne
//	@JoinColumn(name="id_usuario")
//	private Usuario usuario;
	
	public Parametro() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	
	

}
