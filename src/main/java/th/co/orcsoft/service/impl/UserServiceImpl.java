package th.co.orcsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.orcsoft.dao.IUserDao;
import th.co.orcsoft.dto.HeaderDto;
import th.co.orcsoft.dto.JwtDto;
import th.co.orcsoft.dto.Model;
//import th.co.orcsoft.dto.StatusJwtDto;
import th.co.orcsoft.dto.UserDto;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserDao iuserdao;

	@Autowired
	private JwtService jwtservice;

	public Model check_user(UserDto userdto) {

		JwtDto jwtdto = new JwtDto();
		Model model = new Model();
		
		try {
			
			if (!iuserdao.findByUsernameAndPassword(userdto.getUsername(), userdto.getPassword()).isEmpty()) {
				jwtdto = jwtservice.gentoken(userdto.getUsername());
				
				model.setBody(jwtdto);
				
			} 
			else {
				
				//Set header
				HeaderDto header = new HeaderDto();
				header.setStatus("Unsuccess"); 
				header.setErrorcode("401"); 
				header.setErrormessage("Username or password not match.");
				
				//Set body
//				jwtdto.setStatus("Username or password not match.");
				jwtdto.setToken("");
				
				//Set response
				model.setHeader(header);
				model.setBody(jwtdto);
			}
			
			return model;
				
		} catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
			//Set header
			HeaderDto header = new HeaderDto();
			header.setStatus("Unsuccess"); 
			header.setErrorcode("500"); 
			header.setErrormessage(ex.getMessage());
			
			//Set body
			jwtdto.setStatus("Error code 500");
			jwtdto.setToken("");
			
			//Set response
			model.setHeader(header);
			model.setBody(jwtdto);
			
			return model;
		
		}
	}
	
//	public StatusJwtDto check_token(String token) throws Exception {
//		
//		try {
//			return jwtservice.check_token(token);
//		} catch (Exception ex) {
//			throw ex;
//		}
//		
//	}
	
	
}
