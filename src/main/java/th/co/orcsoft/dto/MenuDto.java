package th.co.orcsoft.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class MenuDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description; 
	
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private String price;
	
	@Column(name="types")
	private String types;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setTypes(String types) {
		this.types = types;
	}
	
	public String getTypes() {
		return types;
	}
	
}
