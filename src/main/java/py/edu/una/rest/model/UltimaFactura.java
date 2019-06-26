package py.edu.una.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ultimas_facturas")
@NamedQueries({ 
	@NamedQuery(name = "UltimaFactura.findAll", query = "SELECT u FROM UltimaFactura u"),
	@NamedQuery(name = "UltimaFactura.findByUsuario", query = "SELECT u FROM UltimaFactura u WHERE u.usuario = :usuario")
})
public class UltimaFactura implements Serializable {
	
	private static final long serialVersionUID = -873931737789254813L;
	
	@Id
	private Integer id;

	private String usuario;
	
	@Column(name="nro_factura")
	private String nroFactura;
	
	public UltimaFactura() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
	
	
}
