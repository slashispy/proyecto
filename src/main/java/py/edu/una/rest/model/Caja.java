package py.edu.una.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="caja")
@NamedQueries({
	@NamedQuery(name="Caja.findAll", query="SELECT c FROM Caja c"),
	@NamedQuery(name="Caja.findByUsuario", query="SELECT c FROM Caja c WHERE c.usuario = :usuario AND c.uso = :uso"),
	@NamedQuery(name="Caja.cajaAbierta", query="SELECT c FROM Caja c WHERE c.estadoCaja = 'A' AND c.uso = :uso AND c.usuario = :usuario")
	})
public class Caja implements Serializable {
	
	private static final long serialVersionUID = 3103838628166979596L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_apertura")
	private Date fechaApertura;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_cierre")
	private Date fechaCierre;
	
	@Column(name="estado_caja")
	private String estadoCaja;
	
	@Column(name="monto_apertura")
	private BigDecimal montoApertura;
	
	@Column(name="monto_cierre")
	private BigDecimal montoCierre;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;
	
	private String uso;
	
	public Caja() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getEstadoCaja() {
		return estadoCaja;
	}

	public void setEstadoCaja(String estadoCaja) {
		this.estadoCaja = estadoCaja;
	}

	public BigDecimal getMontoApertura() {
		return montoApertura;
	}

	public void setMontoApertura(BigDecimal montoApertura) {
		this.montoApertura = montoApertura;
	}

	public BigDecimal getMontoCierre() {
		return montoCierre;
	}

	public void setMontoCierre(BigDecimal montoCierre) {
		this.montoCierre = montoCierre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}
	
	
	
	

}
