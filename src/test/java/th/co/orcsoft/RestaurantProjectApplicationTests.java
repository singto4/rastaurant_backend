package th.co.orcsoft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import th.co.orcsoft.controller.MenuController;
import th.co.orcsoft.controller.OrderController;
import th.co.orcsoft.dto.BillDto;
import th.co.orcsoft.dto.MenuDto;
import th.co.orcsoft.dto.OrderDto;

@SpringBootTest
class RestaurantProjectApplicationTests {

	@Autowired
	private MenuController menucontroller;
	
	@Autowired
	private OrderController orderController;
	
//	@Test
//	public void TestGetMenuList() {
//		HashMap<String, List<MenuDto>> data = menucontroller.getListMenu();
//		int result = data.get("menu").size();
//		assertEquals(8, result);
//	}
	
//	@Test
//	public void TestGetMenuListFalse() {
//		HashMap<String, List<MenuDto>> data = menucontroller.getListMenu();
//		int result = data.get("menu").size();
//		assertNotEquals(9, result);
//	}
//	
//	@Test
//	public void TestGetMenuById() throws Exception {
//		int id = 1;
//		MenuDto data = menucontroller.getMenubyId(id);
//		int result = data.getId();
//		assertEquals(id, result);
//	}
//	
//	@Test
//	public void TestGetMenuByIdFalse() throws Exception {
//		int id = 1;
//		MenuDto data = menucontroller.getMenubyId(id);
//		int result = data.getId();
//		assertNotEquals(id+1, result);
//	}
	
//	@Test
//	public void TestGetMenuByName() throws Exception {
//		String name = "Hawaiian Pizza";
//		MenuDto data = menucontroller.getMenubyName(name);
//		String result = data.getName();
//		assertEquals(name, result);
//	}
	
//	@Test
//	public void TestGetMenuByNameFalse() throws Exception {
//		String name = "Hawaiian Pizza";
//		MenuDto data = menucontroller.getMenubyName(name);
//		String result = data.getName();
//		assertNotEquals("Beer", result);
//	}
	
//	@Test
//	public void TestGetMenuByKeyword() throws Exception {
//		String keyword = "Pizza"; // 2 record in db
//		HashMap<String, List<MenuDto>> data = menucontroller.getListMenubyKeyword(keyword);
//		int result = data.get("menu").size();
//		assertEquals(2, result);
//	}
	
//	@Test
//	public void TestGetMenuByKeywordFalse() throws Exception {
//		String keyword = "Pizza"; // 2 record in db
//		HashMap<String, List<MenuDto>> data = menucontroller.getListMenubyKeyword(keyword);
//		int result = data.get("menu").size();
//		assertNotEquals(3, result);
//	}
	
//	@Test
//	public void TestAddMenu() throws Exception {
//		MenuDto menudto = new MenuDto();
//		menudto.setName("Fried Rice.");
//		menudto.setDescription("Rice stir-fried in a wok with scrambled eggs, onions, carrots, green peas and your choice of meat or seafood.");
//		menudto.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQk5aV9QqyLnsw9XxEnC2kGZsmL2g4SrsRYTyF3EXp4q_92CJpM&usqp=CAU");
//		menudto.setPrice("$100");
//		menudto.setTypes("Thai Food");
//		boolean result = menucontroller.AddMenu(menudto);
//		assertEquals(true, result);	
//	}
//	
//	@Test
//	public void TestAddMenuFales() throws Exception {
//		MenuDto menudto = new MenuDto();
//		menudto.setName("Som Tum Thai");
//		menudto.setDescription("Test3");
//		menudto.setImage("Som tum Thai is a fantastic raw made of shredded unripe papaya, sliced tomatoes, raw yardlong beans, dried mini shrimp, etc.");
//		menudto.setPrice("$90");
//		menudto.setTypes("Thai Food");
//		boolean result = menucontroller.AddMenu(menudto);
//		assertNotEquals(false, result);	
//	}
//	
//	@Test
//	public void TestDeleteMenuById() throws Exception {
//		int id = 36;
//		boolean result = menucontroller.DelectMenubyId(id);
//		assertEquals(true, result);	
//	}
//	
//	@Test
//	public void TestDeleteMenuByIdFalse() throws Exception {
//		int id = 37;
//		boolean result = menucontroller.DelectMenubyId(id);
//		assertNotEquals(false, result);	
//	}
	
//	@Test
//	public void TestDeleteMenuById_() throws Exception {
//		int id = 100;
//		boolean result = menucontroller.DelectMenubyId(id);
//		assertEquals(false, result);	
//	}
	
//	@Test
//	public void TestGetOrder() throws Exception {
//		HashMap<String, List<OrderDto>> data = orderController.getOrder();
//		int result = data.get("order").size();
//		assertEquals(28, result);	
//	}
	
//	@Test
//	public void TestGetOrderFalse() throws Exception {
//		HashMap<String, List<OrderDto>> data = orderController.getOrder();
//		int result = data.get("order").size();
//		assertNotEquals(21, result);	
//	}

//	@Test
//	public void TestGetBillbyId() {
//		String bill = "1";
//		HashMap<String, ArrayList<BillDto>> data = orderController.getBill(bill);
//		int result = data.get("bill").size();
//		assertEquals(4, result);
//	}
	
//	@Test
//	public void TestGetBillbyIdFalse() {
//		String bill = "1";
//		HashMap<String, ArrayList<BillDto>> data = orderController.getBill(bill);
//		int result = data.get("bill").size();
//		assertNotEquals(3, result);
//	}
	
//	@Test
//	public void TestAddOrder() {
//		ArrayList<OrderDto> data = new ArrayList<OrderDto>();
//		OrderDto orderdto = new OrderDto();
//		orderdto.setBill("5");
//		orderdto.setMenu("Pumpkin Soup");
//		orderdto.setQuantity(1);
//		
//		data.add(orderdto);
//		
//		String result = orderController.addOrder(data);
//		assertEquals("Add 1 order", result);
//	}
	
//	@Test
//	public void TestAddOrderFalse() {
//		ArrayList<OrderDto> data = new ArrayList<OrderDto>();
//		OrderDto orderdto = new OrderDto();
//		orderdto.setBill("5");
//		orderdto.setMenu("Pumpkin Soup");
//		orderdto.setQuantity(1);
//		
//		data.add(orderdto);
//		
//		String result = orderController.addOrder(data);
//		assertNotEquals("Add 2 order", result);
//	}
	
//	@Test
//	public void TestDeleteById(){
//		int id = 76;
//		boolean result = orderController.deleteOrder(id);
//		assertEquals(true, result);
//	}
	
//	@Test
//	public void TestDeleteByIdFalse(){
//		int id = 77;
//		boolean result = orderController.deleteOrder(id);
//		assertNotEquals(false, result);
//	}
//
}
