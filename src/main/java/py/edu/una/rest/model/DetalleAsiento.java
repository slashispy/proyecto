package py.edu.una.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="asientos_det")
@NamedQuery(name="DetalleAsiento.findAll", query="SELECT d FROM DetalleAsiento d")
public class DetalleAsiento implements Serializable {

	private static final long serialVersionUID = 3103838628166979596L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_asiento" )
	private Asiento asiento;
	
	@Column(name="monto_debe", nullable=true)
	private double montoDebe;
	
	@Column(name="monto_haber", nullable=true)
	private double montoHaber;
	
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	private CuentaContable cuentaContable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}


	public CuentaContable getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(CuentaContable cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public double getMontoDebe() {
		return montoDebe;
	}

	public void setMontoDebe(double montoDebe) {
		this.montoDebe = montoDebe;
	}

	public double getMontoHaber() {
		return montoHaber;
	}

	public void setMontoHaber(double montoHaber) {
		this.montoHaber = montoHaber;
	}
	
	
	
	
	
}
