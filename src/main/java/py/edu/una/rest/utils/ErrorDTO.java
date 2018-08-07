package py.edu.una.rest.utils;

public class ErrorDTO {
	private String mensajeError;
	
	public ErrorDTO(String var) {
		this.mensajeError = var;
	}

	public String getMensajeError() {
		return mensajeError;
	}
}
