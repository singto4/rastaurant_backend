package th.co.orcsoft.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session")
public class SessionDto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="start_date")
	private long start_date;
	
	@Column(name="expiration_date")
	private long expiration_date;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getStart_date() {
		return start_date;
	}

	public void setStart_date(long start_date) {
		this.start_date = start_date;
	}

	public long getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(long expiration_date) {
		this.expiration_date = expiration_date;
	}
}
