package th.co.orcsoft.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.BillDto;
import th.co.orcsoft.dto.BillStatusDto;
import th.co.orcsoft.dto.OrderDto;

@Repository
public class OrderDaoImpl {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	// Get order
	public List<OrderDto> getOrder() {

		List<OrderDto> list = null;
		boolean bool = GetStatusBill("3");
		System.out.println(bool);

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM order_bill");

			Query query = entityManager.createNativeQuery(sql.toString(), OrderDto.class);

			list = query.getResultList();

		} catch (Exception ex) {

			System.out.println(ex.getMessage());
			return list;
		}

		return list;

	}

	//Get bill status
	public boolean GetStatusBill(String bill) {

		List<BillStatusDto> list = null;
		boolean bool = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM bill_status WHERE bill = :bill AND status = :status");
		Query query = entityManager.createNativeQuery(sql.toString(), BillStatusDto.class);
		query.setParameter("bill", bill);
		query.setParameter("status", "doing");

		list = query.getResultList();

		if (list.size() == 0) {
			
			bool = true;
			
		} else {
			
			bool = false;
			
		}

		return bool;
	};

	//Add bill status
	public void AddBillStatus(String bill) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO bill_status (bill, status) VALUES (:bill, :status)");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter("bill", bill);
		query.setParameter("status", "doing");

		int result = query.executeUpdate();

	}

	// Add order
	@Transactional
	public String addOrder(ArrayList<OrderDto> orderdto) {

		int sum = 0;
		String bill = orderdto.get(0).getBill();
		
		//Check status bill
		boolean bool = GetStatusBill(bill);

		if (bool == true) {
			
			//Add bill 
			AddBillStatus(orderdto.get(0).getBill());

			try {

				for (int i = 0; i < orderdto.size(); i++) {

					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
					String time = dateFormat.format(new Date()).toString();
					orderdto.get(i).setTime(time);

					StringBuilder sql = new StringBuilder();
					sql.append(
							"INSERT INTO order_bill (bill, menu, quantity, orderd_time) VALUES (:bill, :menu, :quantity, :orderd_time)");
					Query query = entityManager.createNativeQuery(sql.toString(), OrderDto.class);
					query.setParameter("bill", orderdto.get(i).getBill());
					query.setParameter("menu", orderdto.get(i).getMenu());
					query.setParameter("quantity", orderdto.get(i).getQuantity());
					query.setParameter("orderd_time", orderdto.get(i).getTime());

					int result = query.executeUpdate();

					if (result > 0) {

						sum = sum + result;

					}
				}
				
			} catch (Exception ex) {

				System.out.print(ex.getMessage());
				return ex.getMessage();
			}
		}
		else {
			
			try {

				for (int i = 0; i < orderdto.size(); i++) {

					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
					String time = dateFormat.format(new Date()).toString();
					orderdto.get(i).setTime(time);

					StringBuilder sql = new StringBuilder();
					sql.append(
							"INSERT INTO order_bill (bill, menu, quantity, orderd_time) VALUES (:bill, :menu, :quantity, :orderd_time)");
					Query query = entityManager.createNativeQuery(sql.toString(), OrderDto.class);
					query.setParameter("bill", orderdto.get(i).getBill());
					query.setParameter("menu", orderdto.get(i).getMenu());
					query.setParameter("quantity", orderdto.get(i).getQuantity());
					query.setParameter("orderd_time", orderdto.get(i).getTime());

					int result = query.executeUpdate();

					if (result > 0) {

						sum = sum + result;

					}
				}
				
			} catch (Exception ex) {

				System.out.print(ex.getMessage());
				return ex.getMessage();
			}
		}

		return "Add " + sum + " order";
	}

	// Delete order by id
	@Transactional
	public boolean deleteOrder(int id) {

		boolean bool = false;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE order_bill WHERE id = :id");

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("id", id);

			int rowDelete = query.executeUpdate();

			if (rowDelete > 0) {
				bool = true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return bool;

		}

		return bool;
	}

	// Get total bill
	public ArrayList<BillDto> getBill(String bill) {

		List<Object[]> list = null;
		ArrayList<BillDto> arr = new ArrayList<BillDto>();

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT order_bill.menu as menu, order_bill.quantity as quantity, menu.price as price FROM menu INNER JOIN order_bill ON menu.name = order_bill.menu WHERE order_bill.bill = :bill");

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("bill", bill);

			list = query.getResultList();

			for (Object[] object : list) {

				BillDto billdto = new BillDto();
				billdto.setMenu(object[0].toString());
				billdto.setQuantity((int) object[1]);
				billdto.setPrice(object[2].toString());

				arr.add(billdto);
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}

		return arr;
	}
	
	// Get all bill
	public List<BillStatusDto> getBillStatus(){
		
		List<BillStatusDto> list = null;
		
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM bill_status WHERE status = :status");
			Query query = entityManager.createNativeQuery(sql.toString(), BillStatusDto.class);
			query.setParameter("status", "doing");
			
			list = query.getResultList();

		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
	
	@Transactional
	public boolean UpdateBillStatus(int id) {
		
		boolean bool = false;
		int result;
		
		try {
		
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE bill_status SET status = :status  WHERE id = :id");
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("id", id);
			query.setParameter("status", "done");
		
			result = query.executeUpdate();
		
			if(result > 0) {
				bool = true;
			}
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return bool;
		
	} 

}
