package py.edu.una.rest.utils;

import java.text.ParseException;

import org.joda.time.DateTime;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import py.edu.una.rest.model.Usuario;


public final class AuthUtils {
	
	private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
	private static final String TOKEN_SECRET = "aliceinwonderland";
	public static final String AUTH_HEADER_KEY = "authorization";
	
	public static String getSubject(String authHeader) throws ParseException, JOSEException {
		return decodeToken(authHeader).getSubject();
	}
	
	public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException {
		SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
		if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
			return signedJWT.getJWTClaimsSet();
		} else {
			throw new JOSEException("Signature verification failed");
		}
	}
	
	public static TokenDTO createToken(String host, Usuario subject) throws JOSEException {
		JWTClaimsSet claim = new JWTClaimsSet();
		claim.setSubject(subject.getUsuario());
		claim.setIssuer(host);
		claim.setIssueTime(DateTime.now().toDate());
		claim.setExpirationTime(DateTime.now().plusMinutes(10).toDate());
//		claim.setExpirationTime(DateTime.now().plusDays(1).toDate());
//		claim.setClaim("roles", getPermisos(new ArrayList<Permiso>()));
		claim.setClaim("nombreUsuario", subject.getUsuario());
		
		JWSSigner signer = new MACSigner(TOKEN_SECRET);
		SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
		jwt.sign(signer);
		
		return new TokenDTO(jwt.serialize());
	}
	

//	private static String[] getPermisos(List<Permiso> permisos){
//		List<String> rls = new ArrayList<>();
//		if(permisos != null && !permisos.isEmpty()) {
//			for (Permiso permiso : permisos) {
//				rls.add(permiso.getCodigo());
//			}
//		}else {
//			rls.add("ALL MIGTHY");
//		}
//		
//		return rls.toArray(new String[0]);
//	}
	
	public static String getSerializedToken(String authHeader) {
		return authHeader.split(" ")[0];
	}
}
