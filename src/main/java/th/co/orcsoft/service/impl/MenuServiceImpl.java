package th.co.orcsoft.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.orcsoft.dao.IMenuDao;
import th.co.orcsoft.dao.impl.MenuDaoImpl;
//import th.co.orcsoft.dto.HeaderDto;
import th.co.orcsoft.dto.MenuDto;
import th.co.orcsoft.dto.Model;


@Service
public class MenuServiceImpl {

	@Autowired
	private MenuDaoImpl menuDaoImpl;
	
	@Autowired
	private IMenuDao menudao;
	
	public HashMap<String, List<MenuDto>> getListMenu() {

		//HashMap<String, List<MenuDto>> map = new HashMap<String, List<MenuDto>>();
		//List<MenuDto> list = menuDaoImpl.getListMenu();
		//map.put("menu", list);
		
		HashMap<String, List<MenuDto>> map = new HashMap<String, List<MenuDto>>();
		List<MenuDto> list = (List<MenuDto>) menudao.findAll();
		map.put("menu", list);
	
		return map;
	}
	
	public MenuDto getMenubyId(int id) {
		
		//MenuDto menu = menuDaoImpl.getListMenubyId(id);
		MenuDto menu = menudao.findById(id);

		return menu;
	}
	
	public MenuDto getMenubyName(String name) {

		//MenuDto menu = menuDaoImpl.getListMenubyName(name);
		MenuDto menu = menudao.findByName(name);

		return menu;
	}
	
	public HashMap<String, List<MenuDto>> getListMenubyKeyword(String keyword) {
		
		HashMap<String, List<MenuDto>> map = new HashMap<String, List<MenuDto>>();
		//List<MenuDto> list = menuDaoImpl.getListMenubyKeyword(keyword);
		List<MenuDto> list = menudao.findByNameOrDescriptionOrTypes(keyword);
		map.put("menu", list);
		
		return map;
	}
	
	public Model CreateMenu(MenuDto menudto) {
		
		//boolean result = menuDaoImpl.CreateMenu(menudto);
		
		Model model = new Model();
		
		try {
			
			menudto.setPrice("$"+menudto.getPrice());
			menudao.save(menudto);
			
		}catch (Exception ex) {
			
			
//			HeaderDto header = new HeaderDto();
//			header.setStatus("Unsuccess");
//			header.setErrorcode("401");
//			header.setErrormessage(ex.getMessage());
//			
//			model.setHeader(header);
			
			System.out.println(ex.getMessage());
			throw ex;
			
		}
		
		return model;
		
	}
	
	public Model DeleteMenu(int id) {
		
		//boolean result = menuDaoImpl.DeleteMenu(id);
		Model model = new Model();
		
		try {
			
			menudao.deleteById(id);
			
		}catch (Exception ex) {
			
//			HeaderDto header = new HeaderDto();
//			header.setStatus("Unsuccess");
//			header.setErrorcode("401");
//			header.setErrormessage(ex.getMessage());
//			
//			model.setHeader(header);
			
			System.out.println(ex.getMessage());
			throw ex;
		}
		
		return model;
	}
	
	public Model UpdateMenu(MenuDto menudto) {
		
		//boolean result = menuDaoImpl.UpdateMenu(menudto);
		Model model = new Model();
		
		try {
			
			MenuDto menu = menudao.findById(menudto.getId());
			menu.setName(menudto.getName());
			menu.setDescription(menudto.getDescription());
			menu.setImage(menudto.getImage());
			menu.setPrice(menudto.getPrice());
			menu.setTypes(menudto.getTypes());
			
			menudao.save(menu);
			
//			int id = menudto.getId();
//			String name = menudto.getName();
//			String description = menudto.getDescription();
//			String image = menudto.getImage();
//			String price = menudto.getPrice();
//			String types = menudto.getTypes();
//			
//			menudao.updateMenuById(id, name, description, image, price, types);
			
			
		}catch (Exception ex) {
			
//			HeaderDto header = new HeaderDto();
//			header.setStatus("Unsuccess");
//			header.setErrorcode("401");
//			header.setErrormessage(ex.getMessage());
//			
//			model.setHeader(header);
			
			System.out.println(ex.getMessage());
			throw ex;
		}
		

		return model;
	}
	
	
}
