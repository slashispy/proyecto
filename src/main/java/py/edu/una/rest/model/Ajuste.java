package py.edu.una.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ajustes_cab")
@NamedQueries({
	@NamedQuery(name="Ajuste.findAll", query="SELECT a FROM Ajuste a"),
	@NamedQuery(name="Ajuste.findByEstado", query="SELECT a FROM Ajuste a WHERE a.estado = :estado")
})
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Ajuste implements Serializable {
	private static final long serialVersionUID = 5338973490605766211L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	private String estado;
	
	private BigDecimal importe;
	
	private BigDecimal descuento;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_ajuste")
	private TipoTransaccion tipoAjuste;
	
	@OneToOne
	@JoinColumn(name="id_asiento", nullable=true)
	private Asiento asiento;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="ajuste")
//	@JoinColumn(name="id_asiento", nullable=false )
	private List<DetalleAjuste> detallesAjuste;
	
	public Ajuste() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public TipoTransaccion getTipoAjuste() {
		return tipoAjuste;
	}

	public void setTipoAjuste(TipoTransaccion tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public List<DetalleAjuste> getDetallesAjuste() {
		for(DetalleAjuste d: detallesAjuste) {
			d.setAjuste(null);
		}
		return detallesAjuste;
	}

	public void setDetallesAjuste(List<DetalleAjuste> detallesAjuste) {
		this.detallesAjuste = detallesAjuste;
	}
	
	

}
