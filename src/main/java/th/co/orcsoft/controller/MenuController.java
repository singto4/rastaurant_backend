package th.co.orcsoft.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import th.co.orcsoft.dto.HeaderDto;
import th.co.orcsoft.dto.MenuDto;
import th.co.orcsoft.dto.Model;
import th.co.orcsoft.service.impl.JwtService;
import th.co.orcsoft.service.impl.MenuServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resteurant/menu")
public class MenuController {
	
	@Autowired
	private MenuServiceImpl menuservice;
	
	@Autowired
	private JwtService jwtservice;
	
	@RequestMapping(method= {RequestMethod.GET}, path="/getlistmenu")
	public HashMap<String, List<MenuDto>> getListMenu() {
		
		return menuservice.getListMenu();
		
	}
	
	@RequestMapping(method= {RequestMethod.GET}, path="/getlistmenu/id/{id}")
	public MenuDto getMenubyId(@PathVariable("id") int id) {
		
		return menuservice.getMenubyId(id);
		
	}
	
	@RequestMapping(method= {RequestMethod.GET}, path="/getlistmenu/name/{name}")
	public MenuDto getMenubyName(@PathVariable("name") String name) {
		
		return menuservice.getMenubyName(name);
		
	}
	
	@RequestMapping(method= {RequestMethod.GET}, path="/getlistmenu/keyword/{keyword}")
	public HashMap<String, List<MenuDto>> getListMenubyKeyword(@PathVariable("keyword") String keyword) {
		
		return menuservice.getListMenubyKeyword(keyword);
		
	}
	
	@RequestMapping(method= {RequestMethod.POST}, path="/createmenu")
	public Model AddMenu(@RequestBody MenuDto menudto, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = menuservice.CreateMenu(menudto);
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
			HeaderDto header = new HeaderDto();
			header.setStatus("Unsuccess");
			header.setErrorcode("401");
			header.setErrormessage(ex.getMessage());
			
			model.setHeader(header);
		}
		
		return model;	
	}
	
	@RequestMapping(method= {RequestMethod.DELETE}, path="/deletemenu/{id}")
	public Model DelectMenubyId(@PathVariable("id") int id, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = menuservice.DeleteMenu(id);
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
			HeaderDto header = new HeaderDto();
			header.setStatus("Unsuccess");
			header.setErrorcode("401");
			header.setErrormessage(ex.getMessage());
			
			model.setHeader(header);
		}
		
		return model;
	}
	
	@RequestMapping(method= {RequestMethod.PUT}, path="/updatemenu")
	public Model UpdateMenu(@RequestBody MenuDto menudto, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = menuservice.UpdateMenu(menudto);
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
			HeaderDto header = new HeaderDto();
			header.setStatus("Unsuccess");
			header.setErrorcode("401");
			header.setErrormessage(ex.getMessage());
			
			model.setHeader(header);
		}
		
		return model;	
	}
}
