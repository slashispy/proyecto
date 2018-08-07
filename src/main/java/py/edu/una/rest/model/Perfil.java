package py.edu.una.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "perfiles")
@NamedQueries({ @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
				@NamedQuery(name = "Perfil.findByCodigo", query = "SELECT p FROM Perfil p WHERE p.codigo = :codigo") 
})
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String codigo;
	private String descripcion;
	private String estado;

	@ManyToMany
	// @NotEmpty
	@JoinTable(name = "perfiles_permisos", joinColumns = { @JoinColumn(name = "id_perfil") }, inverseJoinColumns = {
			@JoinColumn(name = "id_permiso") })
	private List<Permiso> permisos;

	public Perfil() {

	}

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

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

}
