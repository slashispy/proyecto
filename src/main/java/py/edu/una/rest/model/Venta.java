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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ventas_cab")
@NamedQueries({
	@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v"),
	@NamedQuery(name="Venta.findByEstado", query="SELECT v FROM Venta v WHERE v.estado = :estado")
})
public class Venta implements Serializable {
	
	private static final long serialVersionUID = 3103838628166979596L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Column(name="nro_factura")
	private String nroFactura;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@NotEmpty
	@JoinColumn(name="id_venta")
	private List<VentaDetalleMedioPago> mediosPago;
	
	private BigDecimal importe;
	
	private BigDecimal descuento;
	
	@ManyToOne
	@JoinColumn(name="id_caja")
	private Caja caja;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_venta")
	private TipoTransaccion tipoVenta;
	
	@OneToOne
	@JoinColumn(name="id_asiento")
	private Asiento asiento;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="venta")
//	@JoinColumn(name="id_asiento", nullable=false )
	private List<DetalleVenta> detalleVenta;
	
	private String estado;
	
	private String timbrado;
	
	public Venta() {}

	public List<DetalleVenta> getDetalleVenta() {
		for (DetalleVenta w: detalleVenta) {
			w.setVenta(null);
		}
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<VentaDetalleMedioPago> getMediosPago() {
		return mediosPago;
	}

	public void setMediosPago(List<VentaDetalleMedioPago> medioPago) {
		this.mediosPago = medioPago;
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

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public TipoTransaccion getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(TipoTransaccion tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(String timbrado) {
		this.timbrado = timbrado;
	}
	
}
