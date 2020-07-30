package th.co.orcsoft.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import th.co.orcsoft.dto.HeaderDto;
import th.co.orcsoft.dto.Model;
import th.co.orcsoft.dto.OrderDto;
import th.co.orcsoft.service.impl.JwtService;
import th.co.orcsoft.service.impl.OrderServiceImpl;
//import th.co.orcsoft.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resteurant/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderservice;
	
	@Autowired
	private JwtService jwtservice;
	
	//Retrieve order 
	@RequestMapping(method= {RequestMethod.GET}, path="/getorder")
	public Model getOrder() {
		
		return orderservice.getOrder();
		
	}
	
	//Add order of arraylist
	@RequestMapping(method= {RequestMethod.POST}, path="/addorder")
	public Model addOrder(@RequestBody ArrayList<OrderDto> order, @RequestHeader String Authorization) {

		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = orderservice.addOrder(order);
			
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
	
	//Delete order by id 
	@RequestMapping(method= {RequestMethod.DELETE}, path="/deleteorder/id/{id}")
	public Model deleteOrder(@PathVariable("id") int id) {
		
		return orderservice.deleteOrder(id);
		
	}
	
	//Get bill order by no.bill
	@RequestMapping(method= {RequestMethod.GET}, path="/getbill/{bill}")
	public Model getBill(@PathVariable("bill") String bill, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = orderservice.getBill(bill);
			
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
	

	//Get bill status 
	@RequestMapping(method= {RequestMethod.GET}, path="/getbillstatus")
	public Model getBillStatus(@RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = orderservice.getBillStatus();
			
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
	
	//Update bill status 
	@RequestMapping(method= {RequestMethod.POST}, path="/updatebillstatus")
	public Model updateBillStatus(@RequestBody int id, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			model = orderservice.UpdateBillStatus(id);
			
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
