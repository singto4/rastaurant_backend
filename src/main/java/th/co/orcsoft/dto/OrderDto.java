package th.co.orcsoft.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_bill")
public class OrderDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
		
	@Column(name="bill")
	private String bill;
		
	@Column(name="menu")
	private String menu;
		
	@Column(name="quantity")
	private int quantity;
		
	@Column(name="Orderd_time")
	private String time;
	
	@Column(name="billcreate_time")
	private String create_time;
	
	
		
	public void setId(int id) {
		this.id = id;
	}
		
	public int getId() {
		return id;
	}
		
	public void setBill(String bill) {
		this.bill = bill;
	}
		
	public String getBill() {
		return bill;
	}
		
	public void setMenu(String menu) {
		this.menu = menu;
	}
		
	public String getMenu() {
		return menu;
	}
		
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
	public int getQuantity() {
		return quantity;
	}
		
	public void setTime(String time) {
		this.time = time;
	}
		
	public String getTime() {
		return time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
