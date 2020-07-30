package th.co.orcsoft.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name="bill_status")
public class BillStatusDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
		
	@Column(name="bill")
	private String bill;
		
	@Column(name="status")
	private String status;
	
	@Column(name="create_time")
	private String time;
	
	@Column(name="update_time")
	private String update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	} 
	
		
			
}
