package th.co.orcsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import th.co.orcsoft.dto.UserDto;
import th.co.orcsoft.service.impl.TestServiceImpl;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestServiceImpl testService;

	@RequestMapping(method= {RequestMethod.GET}, path="hello/{name}")
	public String getName(@PathVariable("name") String name) {
		return "Hello, " + name;
	}

	@RequestMapping(method= {RequestMethod.GET}, path="saveUser/{username}")
	public UserDto saveUserByName(@PathVariable("username") String username) {
		return testService.saveUser(username);
	}
	

	@RequestMapping(method= {RequestMethod.GET}, path="getUser/{username}")
	public UserDto getUserByUserName(@PathVariable("username") String username) {
		return testService.getUserByUserName(username);
	}


}
