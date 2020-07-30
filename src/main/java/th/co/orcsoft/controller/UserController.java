package th.co.orcsoft.controller;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import th.co.orcsoft.dto.JwtDto;
import th.co.orcsoft.dto.Model;
//import th.co.orcsoft.dto.StatusJwtDto;
import th.co.orcsoft.dto.UserDto;
import th.co.orcsoft.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resteurant/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userservice;
	
	@RequestMapping(method= {RequestMethod.POST}, path="/login")
	public Model Login(@RequestBody UserDto userdto ) throws MalformedClaimException, JoseException {
		
		return userservice.check_user(userdto);
					
	}
	
	
//	@RequestMapping(method= {RequestMethod.POST}, path="/checktoken")
//	public StatusJwtDto check_token(@RequestBody String token ) {
//		
//		try {
//			return userservice.check_token(token);
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//					
//	}
	
	
}
