package py.edu.una.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ventas_det_medio_pago")
@NamedQuery(name="VentaDetalleMedioPago.findAll", query="SELECT v FROM VentaDetalleMedioPago v")
public class VentaDetalleMedioPago implements Serializable {

	private static final long serialVersionUID = -4019989015921115650L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal monto;

	@ManyToOne
	@JoinColumn(name="id_medio_pago")
	private MedioPago medioPago;
	
	private BigDecimal vuelto;
	
	public VentaDetalleMedioPago() {}

	public BigDecimal getVuelto() {
		return vuelto;
	}

	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	public MedioPago getMedioPago() {
		return medioPago;
	}
	
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

}
