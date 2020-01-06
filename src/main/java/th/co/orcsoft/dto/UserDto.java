package th.co.orcsoft.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REST_USER")
public class UserDto {
	@Id
	@GeneratedValue(
			strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	@Column(name="USERNAME", unique=true, length=255)
	private String userName;
	@Column(name="PASSWORD", length=255)
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

