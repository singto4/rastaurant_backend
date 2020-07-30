package th.co.orcsoft.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.orcsoft.dao.IBillStatusDao;
import th.co.orcsoft.dao.IOrderDao;
import th.co.orcsoft.dto.BillDto;
import th.co.orcsoft.dto.BillStatusDto;
import th.co.orcsoft.dto.Model;
import th.co.orcsoft.dto.OrderDto;

//@Transactional
@Service
public class OrderServiceImpl {

//	@Autowired
//	private OrderDaoImpl orderDaoImpl;
	
	@Autowired
	private IOrderDao orderdao;
	
	@Autowired
	private IBillStatusDao billstatusdao;
	
//	@Autowired
//	private IMenuDao menudao;


	public Model getOrder() {

//		HashMap<String, List<OrderDto>> map = new HashMap<String, List<OrderDto>>();
//		List<OrderDto> list = orderDaoImpl.getOrder();
//		map.put("order", list);

		Model model = new Model();
		//HeaderDto header = new HeaderDto();
		HashMap<String, List<OrderDto>> map = new HashMap<String, List<OrderDto>>();
		
		try {
			
			List<OrderDto> list = orderdao.findAll();
			map.put("order", list);
			model.setBody(map);
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			throw ex;
			
		}
		
		return model;
	}

	public Model addOrder(ArrayList<OrderDto> orderdto) {

		//String result = orderDaoImpl.addOrder(orderdto);
		Model model = new Model();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
		String time;
		
		try {
		
			List<BillStatusDto> list = billstatusdao.findByBillAndStatus(orderdto.get(0).getBill(), "doing");
		
			if(list.size() == 0) {
				
				
				String create_time = dateFormat.format(new Date()).toString();
			
				BillStatusDto billstatus = new BillStatusDto();
				billstatus.setBill(orderdto.get(0).getBill());
				billstatus.setStatus("doing");
				billstatus.setTime(create_time);
			
				billstatusdao.save(billstatus);
				
				for(int i = 0; i < orderdto.size(); i++) {
					
					time = dateFormat.format(new Date()).toString();
					orderdto.get(i).setTime(time);
					orderdto.get(i).setCreate_time(create_time);
					
				}
				
				orderdao.saveAll(orderdto);
	
			} 
			else {
				
				String create_time = list.get(0).getTime();
				
				for(int i = 0; i < orderdto.size(); i++) {
					
					time = dateFormat.format(new Date()).toString();
					orderdto.get(i).setTime(time);
					orderdto.get(i).setCreate_time(create_time);
					
				}
				
				orderdao.saveAll(orderdto);
			}
		
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			throw ex;

		}
		
		return model;

	}

	public Model deleteOrder(int id) {

		//boolean result = orderDaoImpl.deleteOrder(id);
		Model model = new Model();
		try {
			
			orderdao.deleteById(id);
		
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			throw ex;
		}

		return model;
	}

	public Model getBill(String bill) {

		HashMap<String, ArrayList<BillDto>> map = new HashMap<String, ArrayList<BillDto>>();
		//ArrayList<BillDto> list = orderDaoImpl.getBill(bill);
		Model model = new Model();
		ArrayList<BillDto> arr = new ArrayList<BillDto>();
		List<Object[]> data = billstatusdao.findByBill(bill);
		
		for(Object[] obj : data) {
			BillDto billdto = new BillDto();
			billdto.setMenu(obj[0].toString());
			billdto.setQuantity((int) obj[1]);
			billdto.setPrice(obj[2].toString());	
			
			arr.add(billdto);
		}
 
		try {

			for (int i = 0; i < arr.size(); i++) {

				int quantity = arr.get(i).getQuantity();
				String price_str = arr.get(i).getPrice().substring(1);
				int price = Integer.parseInt(price_str);

				int total = quantity * price;

				arr.get(i).setTotal(total);
			}

			map.put("bill", arr);
			
			model.setBody(map);
			
		} catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			throw ex;
		}

		return model;
	}
	
	public Model getBillStatus() {
		
//		HashMap<String, List<BillStatusDto>> map = new HashMap<String, List<BillStatusDto>>();
//		List<BillStatusDto> list = orderDaoImpl.getBillStatus();
//		map.put("billstatus", list);
		Model model = new Model();
		try {
			
			HashMap<String, List<BillStatusDto>> map = new HashMap<String, List<BillStatusDto>>();
			//List<BillStatusDto> list = billstatusdao.findAllByOrderByStatusAsc();
			List<BillStatusDto> list = billstatusdao.findAllByOrderByStatusAndTime();
			map.put("billstatus", list);
			
			model.setBody(map);
		
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			throw ex;
		}
		
		return model;
		
	}

	public Model UpdateBillStatus(int id) {
		
		Model model = new Model();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
		
		try {
			
			//orderDaoImpl.UpdateBillStatus(id);
			String time = dateFormat.format(new Date()).toString();
			
			BillStatusDto billstatus = billstatusdao.findById(id);
			billstatus.setStatus("done");
			billstatus.setUpdate_time(time);
			
			billstatusdao.save(billstatus);
			
			//billstatusdao.updateBillStatusById(id, "done", time);
			
		}catch (Exception ex) {

//			System.out.println(ex.getMessage());
			ex.printStackTrace();
			throw ex;
			
		}
		
		return model;
		
	}
}
