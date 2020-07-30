package th.co.orcsoft.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.MenuDto;

@Repository
public class MenuDaoImpl {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	// Retrieve menu
	public List<MenuDto> getListMenu() {
		
		List<MenuDto> list = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM menu");

			Query query = entityManager.createNativeQuery(sql.toString(), MenuDto.class);

			list = query.getResultList();

		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}

		return list;
	}

	// Searching menu by id
	public MenuDto getListMenubyId(int id) {

		MenuDto menudto = null;

		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM menu WHERE id = :id");

			Query query = entityManager.createNativeQuery(sql.toString(), MenuDto.class);
			query.setParameter("id", id);

			menudto = (MenuDto) query.getSingleResult();
			
		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			return menudto;
		}
		
		return menudto;
	}

	// Searching menu by name
	public MenuDto getListMenubyName(String name) {

		MenuDto menudto = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM menu WHERE name = :name");

			Query query = entityManager.createNativeQuery(sql.toString(), MenuDto.class);
			query.setParameter("name", name);

			menudto = (MenuDto) query.getSingleResult();

		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			return menudto;
		}

		return menudto;
	}

	// Searching menu by keyword
	public List<MenuDto> getListMenubyKeyword(String keyword) {

		List<MenuDto> list = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM menu WHERE name LIKE :keyword OR description LIKE :keyword OR types LIKE :keyword");

			Query query = entityManager.createNativeQuery(sql.toString(), MenuDto.class);
			query.setParameter("keyword", "%" + keyword + "%");

			list = query.getResultList();

		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}

		return list;
	}
	
	//Get max id 
	public int GetMaxId() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MAX(id) AS id FROM menu");
		Query query = entityManager.createNativeQuery(sql.toString());
		int id_max = (int) query.getSingleResult();
		
		return id_max;
	}

	// Create menu
	@Transactional
	public boolean CreateMenu(MenuDto menudto) {

		boolean bool = false;
		//int id_max = GetMaxId();
	
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO menu (name, description, image, price, types) VALUES (:name, :description, :image, :price, :types)");
			//sql.append("INSERT INTO menu (id, name, description, image, price, types) VALUES (:id, :name, :description, :image, :price, :types)");
			Query query = entityManager.createNativeQuery(sql.toString(), MenuDto.class);
			//query.setParameter("id", id_max + 1);
			query.setParameter("name", menudto.getName());
			query.setParameter("description", menudto.getDescription());
			query.setParameter("image", menudto.getImage());
			query.setParameter("price", menudto.getPrice());
			query.setParameter("types", menudto.getTypes());

			int result = query.executeUpdate();

			if (result > 0) {
				bool = true;
			}

		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}

		return bool;
	}

	// Delete menu by id
	@Transactional
	public boolean DeleteMenu(int id) {

		boolean bool = false;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM menu WHERE id = :id");

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("id", id);

			int rowDelete = query.executeUpdate();

			if (rowDelete > 0) {
				bool = true;
			}

		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}

		return bool;
	}

	// Update menu by id
	@Transactional
	public boolean UpdateMenu(MenuDto menudto) {

		boolean bool = false;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE menu SET name = :name, description = :description, image = :image, price = :price, types = :types WHERE id = :id");

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("id", menudto.getId());
			query.setParameter("name", menudto.getName());
			query.setParameter("description", menudto.getDescription());
			query.setParameter("image", menudto.getImage());
			query.setParameter("price", menudto.getPrice());
			query.setParameter("types", menudto.getTypes());

			int rowUpdate = query.executeUpdate();

			if (rowUpdate > 0) {
				bool = true;
			}
		} 
		catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}

		return bool;
	}
}
