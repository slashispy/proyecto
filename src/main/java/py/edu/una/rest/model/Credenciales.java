package py.edu.una.rest.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Credenciales implements Serializable{

	private static final long serialVersionUID = 2878989396846996611L;

	@NotNull
	private String usuario;
	@NotNull
	private String password;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
