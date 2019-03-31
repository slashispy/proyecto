package py.edu.una.rest.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;

import py.edu.una.rest.model.Credenciales;
import py.edu.una.rest.model.Usuario;
import py.edu.una.rest.services.UsuarioService;
import py.edu.una.rest.utils.AuthUtils;
import py.edu.una.rest.utils.ErrorDTO;
import py.edu.una.rest.utils.PasswordService;
import py.edu.una.rest.utils.TokenDTO;

@CrossOrigin
@Controller
@RequestMapping(value="/auth", consumes="application/json", produces="application/json")
public class AuthControllers {
	
	@Autowired
	private UsuarioService service;
	
//	private static final String CLIENT_ID_KEY = "client_id";
//	private static final String REDIRECT_URI_KEY = "redirect_uri";
//	private static final String CLIENT_SECRET = "client_secret";
//	private static final String CODE_KEY = "code";
//	private static final String GRANT_TYPE_KEY = "grant_type";
//	private static final String AUTH_CODE = "authorization_code";
//	
	private static final String CONFLICT_MSG = "%s, ya existe una cuenta que te pertenece";
	private static final String NOT_FOUND_MSG = "Usuario no encontrado";
	private static final String LOGING_ERROR_MSG = "Usuario y/o clave no validos";
	
	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody @Valid Credenciales credenciales, final HttpServletRequest request)
			throws JOSEException {
		final Optional<Usuario> foundUser = service.getByUsuario(credenciales.getUsuario());
		if (foundUser.isPresent()){
			if(PasswordService.checkPassword(credenciales.getPassword(), foundUser.get().getClave())) {
//				List<Rol> roles = dao.getRoles(credenciales.getUsuario());
				final TokenDTO token = AuthUtils.createToken(request.getRemoteHost(), foundUser.get());
				return new ResponseEntity<TokenDTO>(token,HttpStatus.OK);
			}
			return new ResponseEntity<ErrorDTO>(new ErrorDTO(LOGING_ERROR_MSG),HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(NOT_FOUND_MSG),HttpStatus.UNAUTHORIZED);	
	}
	
	@RequestMapping(value="registrer", method=RequestMethod.POST)
	public ResponseEntity<?> registrer(@RequestBody @Valid Usuario credenciales, final HttpServletRequest request)
			throws JOSEException {
		try {
			System.out.println(MAPPER.writeValueAsString(credenciales));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		final Optional<Usuario> foundUser = service.getByUsuario(credenciales.getUsuario());
		if (foundUser.isPresent()){
			return new ResponseEntity<ErrorDTO>(new ErrorDTO(String.format(CONFLICT_MSG,credenciales.getUsuario())),HttpStatus.UNAUTHORIZED);
		}else {
			credenciales.setClave(PasswordService.hashPassword(credenciales.getClave()));
			Usuario savedUser = service.insertar(credenciales);
			final TokenDTO token = AuthUtils.createToken(request.getRemoteHost(), savedUser);
			return new ResponseEntity<TokenDTO>(token,HttpStatus.CREATED);
		}	
	}
	

}
