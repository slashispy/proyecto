package py.edu.una.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name="compras_cab")
@NamedQueries({
	@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c"),
	@NamedQuery(name="Compra.findByEstado", query="SELECT c FROM Compra c WHERE c.estado = :estado"),
	@NamedQuery(name="Compra.informe", query="SELECT c FROM Compra c WHERE c.estado = :estado AND c.fecha BETWEEN :desde AND :hasta")
})
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Compra implements Serializable {
	
	private static final long serialVersionUID = 3103838628166979596L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Column(name="nro_factura")
	private String nroFactura;
	
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	private String estado;
	
	private String timbrado;
	
	private BigDecimal importe;
	
	private BigDecimal descuento;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_compra")
	private TipoTransaccion tipoCompra;
	
	@OneToOne
	@JoinColumn(name="id_asiento", nullable=true)
	private Asiento asiento;
	
	
	@ManyToOne
	@JoinColumn(name="id_caja", nullable = true)
	private Caja caja;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="compra")
//	@JoinColumn(name="id_asiento", nullable=false )
	private List<DetalleCompra> detalleCompras;
	
	public Compra() {}

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

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

	public TipoTransaccion getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(TipoTransaccion tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public List<DetalleCompra> getDetalleCompras() {
		for (DetalleCompra w: detalleCompras) {
			w.setCompra(null);
		}
		return detalleCompras;
	}

	public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public String getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(String timbrado) {
		this.timbrado = timbrado;
	}
	
	
	
	
	
	

}
