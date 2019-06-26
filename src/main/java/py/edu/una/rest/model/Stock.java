package py.edu.una.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="stock")
@NamedQueries({ 
	@NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s WHERE s.controlarStock = 'S'"),
	@NamedQuery(name = "Stock.findByControlarStock", query = "SELECT s FROM Stock s WHERE s.controlarStock = :controlarStock")
})
public class Stock implements Serializable {

	private static final long serialVersionUID = -873931737789254813L;
	
	@Id
	@Column(name="id_producto")
	private Integer idProducto;
	
	@Column(name="desc_producto")
	private String descProducto;
	
	@Column(name="controlar_stock")
	private String controlarStock;
	
	@Column(name="cantidad_entrada")
	private BigDecimal cantidadEntrada;
	
	@Column(name="cantidad_baja")
	private BigDecimal cantidadBaja;
	
	private BigDecimal existencias;
	
	@Column(name="importe_gastado")
	private BigDecimal importeGastado;
	
	@Column(name="importe_entrada")
	private BigDecimal importeEntrada;
	
	@Column(name="precio_promedio")
	private BigDecimal precioPromedio;
	
	@Column(name="precio_venta")
	private BigDecimal precioVenta;
	
	public Stock () {}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public String getControlarStock() {
		return controlarStock;
	}

	public void setControlarStock(String controlarStock) {
		this.controlarStock = controlarStock;
	}

	public BigDecimal getCantidadEntrada() {
		return cantidadEntrada;
	}

	public void setCantidadEntrada(BigDecimal cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	public BigDecimal getCantidadBaja() {
		return cantidadBaja;
	}

	public void setCantidadBaja(BigDecimal cantidadBaja) {
		this.cantidadBaja = cantidadBaja;
	}

	public BigDecimal getExistencias() {
		return existencias;
	}

	public void setExistencias(BigDecimal existencias) {
		this.existencias = existencias;
	}

	public BigDecimal getImporteGastado() {
		return importeGastado;
	}

	public void setImporteGastado(BigDecimal importeGastado) {
		this.importeGastado = importeGastado;
	}

	public BigDecimal getImporteEntrada() {
		return importeEntrada;
	}

	public void setImporteEntrada(BigDecimal importeEntrada) {
		this.importeEntrada = importeEntrada;
	}

	public BigDecimal getPrecioPromedio() {
		return precioPromedio;
	}

	public void setPrecioPromedio(BigDecimal precioPromedio) {
		this.precioPromedio = precioPromedio;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	
	

}
