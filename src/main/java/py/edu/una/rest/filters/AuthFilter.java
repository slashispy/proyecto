package py.edu.una.rest.filters;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import py.edu.una.rest.utils.AuthUtils;

public class AuthFilter implements Filter {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	
	private static final String AUTH_ERROR_MSG = "Asegurate que tu peticion tenga un Authorization header",
								EXPIRE_ERROR_MSG = "El Token ha expirado",
								JWT_ERROR_MSG = "Error al parsear JWT",
								JWT_INVALID_MSG = "Token JWT invalido";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(((HttpServletRequest)request).getRequestURI().endsWith("/auth/login") 
				|| ((HttpServletRequest)request).getRequestURI().endsWith("/auth/signup"))){

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String authHeader = httpRequest.getHeader(AuthUtils.AUTH_HEADER_KEY);
			if (StringUtils.isEmpty(authHeader) || authHeader.split(" ").length != 1) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, AUTH_ERROR_MSG);
			} else {
				JWTClaimsSet claimSet = null;
				try {
					claimSet = (JWTClaimsSet) AuthUtils.decodeToken(authHeader);
				} catch (ParseException e) {
					httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_ERROR_MSG);
					return;
				} catch (JOSEException e) {
					httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_INVALID_MSG);
					return;
				}
				// ensure that the token is not expired
				if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPIRE_ERROR_MSG);
				} else {
					chain.doFilter(request, response);
				}
			}
		}else{
			chain.doFilter(request, response);
		}
	}

    public void destroy() { /* unused */ }

    public void init(FilterConfig filterConfig) throws ServletException { /* unused */ }

}
