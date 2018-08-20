package py.edu.una.rest.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {
	String token;

	public TokenDTO(@JsonProperty("token") String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
