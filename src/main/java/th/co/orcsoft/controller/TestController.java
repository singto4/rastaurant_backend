package th.co.orcsoft.controller;

//import java.util.HashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import th.co.orcsoft.dto.HeaderDto;
//import th.co.orcsoft.dto.MenuDto;
//import th.co.orcsoft.dto.Model;
//import th.co.orcsoft.dto.StatusJwtDto;
//import th.co.orcsoft.dto.UserDto;
//import th.co.orcsoft.service.impl.MenuServiceImpl;
//import th.co.orcsoft.service.impl.TestServiceImpl;

@RestController
@RequestMapping("/test")
public class TestController {
	
//	@Autowired
//	private TestServiceImpl testService;
	
	@RequestMapping(method= {RequestMethod.GET}, path="hello/{name}")
	public String getName(@PathVariable("name") String name) {
		
		return "Hello, " + name;
		
	}

//	@RequestMapping(method= {RequestMethod.GET}, path="saveUser/{username}")
//	public UserDto saveUserByName(@PathVariable("username") String username) {
//		
//		return testService.saveUser(username);
//		
//	}
//	
//
//	@RequestMapping(method= {RequestMethod.GET}, path="getUser/{username}")
//	public UserDto getUserByUserName(@PathVariable("username") String username) {
//		
//		return testService.getUserByUserName(username);
//		
//	}

//	@RequestMapping(method= {RequestMethod.GET}, path="/model")
//	public Model getName() {
//		
//		StatusJwtDto jwt = new StatusJwtDto();
//		jwt.setStatus(true);
//		
//		HeaderDto header = new HeaderDto();
//		header.setStatus("good");
//		header.setErrorcode("200");
//		header.setErrormessage("");
//
//		Model model = new Model();
//		model.setHeader(header);
//		model.setBody(jwt);
//		
//		return model;
//		
//	}
	
//	@RequestMapping(method= {RequestMethod.GET}, path="/getlistmenu/keyword/{keyword}")
//	public Model getListMenubyKeyword(@PathVariable("keyword") String keyword) {
//		
//		Object obj = testService.getListMenubyKeyword(keyword);
//		
//		HeaderDto header = new HeaderDto();
//		header.setStatus("good");
//		header.setErrorcode("200");
//		header.setErrormessage("");
//		
//		Model model = new Model();
//		model.setHeader(header);
//		model.setBody(obj);
//		
//		return model;
//		
//	}

}
