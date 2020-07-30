package th.co.orcsoft.service.impl;

import java.security.Key;

import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
//import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.AesKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.orcsoft.dao.ISessionDao;
//import th.co.orcsoft.dao.impl.JwtDaoImpl;
import th.co.orcsoft.dto.JwtDto;
import th.co.orcsoft.dto.SessionDto;
import th.co.orcsoft.dto.StatusJwtDto;


@Service
public class JwtService {
	
//	@Autowired
//	private IUserDao iuserdao;
	
//	@Autowired
//	private JwtDaoImpl jwtdaoimpl;
	
	@Autowired
	private ISessionDao isessiondao;

	//Gen token 
	public JwtDto gentoken(String username) {
		
		String token = "";
		JwtDto jwtdto = new JwtDto();
		long id = isessiondao.findMaxId();
		
	    // Create key
	    String str = "TipakornTipakornTipakornTipakorn";
	    Key key = new AesKey(str.getBytes());
	    
	    JwtClaims claims = new JwtClaims();
	    claims.setIssuer("Restaurant"); 
	    claims.setExpirationTimeMinutesInTheFuture(300); 
	    claims.setIssuedAtToNow();  
	    claims.setClaim("id", id + 1);
	    
	    try {
	    
	    	JsonWebSignature jws = new JsonWebSignature();
	    	jws.setPayload(claims.toJson());
	    	jws.setKey(key);
	    	jws.setKeyIdHeaderValue(key.getAlgorithm());
	    	jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
	    
	    	token = jws.getCompactSerialization();
	    	
	    	SessionDto sessiondto = new SessionDto();
	    	sessiondto.setUsername(username);
	    	sessiondto.setStart_date(claims.getIssuedAt().getValue());
	    	sessiondto.setExpiration_date(claims.getExpirationTime().getValue());
	    	
	    	isessiondao.save(sessiondto);
	    	
	    	//jwtdto.setStatus("200");
	    	jwtdto.setToken(token);
	    	
	    	// System.out.println("Token: " + token);
	    	
	    }catch (Exception ex) {
	    	
			System.out.println(ex.getMessage());
			jwtdto.setStatus("500");
	    	jwtdto.setToken(token);
	
		}
	    
	    return jwtdto;
	}
	
	
	//Vadidate token and check expriration date
	public StatusJwtDto check_token(String token) throws Exception {
		
		String str = "TipakornTipakornTipakornTipakorn";
		Key key = new AesKey(str.getBytes());
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				//.setRequireExpirationTime()
				.setVerificationKey(key) 
				.setJwsAlgorithmConstraints(ConstraintType.WHITELIST, AlgorithmIdentifiers.HMAC_SHA256) 
				.build(); 
		
		StatusJwtDto statusjwt = new StatusJwtDto();
		
		
		try {
			
			jwtConsumer.processToClaims(token);
			
			
			//Check exp in database.
			//JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			
			//long date_time = NumericDate.now().getValue();
			//long id = Long.parseLong(jwtClaims.getClaimValue("id").toString());

			//boolean status = jwtdaoimpl.check_exp(id, date_time);
					
			//statusjwt.setStatus(status);
			
			//System.out.println(jwtClaims.getClaimValue("id"));
			//System.out.println(jwtClaims.getClaimValue("iat")); //Start date
			//System.out.println(jwtClaims.getClaimValue("exp")); //End date
			//System.out.println(date_time);
			
			statusjwt.setStatus(true);
			
			return statusjwt;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception("Session timeout");
			//throw ex;
		} 
		
	}
}
