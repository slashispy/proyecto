package py.edu.una.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="productos")
@NamedQueries({
	@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p"),
	@NamedQuery(name="Producto.findByCodigo", query="SELECT p FROM Producto p WHERE p.codigo = :codigo"),
	@NamedQuery(name="Producto.findByEstado", query="SELECT p FROM Producto p WHERE p.estado = :estado"),
	@NamedQuery(name="Producto.findByControlarStock",query="SELECT p FROM Producto p WHERE p.controlarStock = :controlarStock AND p.estado = 'A'")
	})
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String codigo;
	
	private String descripcion;
	
	private String estado;
	
	@Column(name="controlar_stock")
	private String controlarStock;
	
	@Column(name="precio_unitario")
	private BigDecimal precioUnitario;
	
	@Column(name="cantidad_minima")
	private BigInteger cantidadMinima;
	
	public Producto() {}

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getControlarStock() {
		return controlarStock;
	}

	public void setControlarStock(String controlarStock) {
		this.controlarStock = controlarStock;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigInteger getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(BigInteger cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	
	

}
