package th.co.orcsoft.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.SessionDto;

@Repository
public class JwtDaoImpl {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	public int getMaxId() {

		int id = 0;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MAX(id) AS id FROM session");

			Query query = entityManager.createNativeQuery(sql.toString());

			id = (int) query.getSingleResult();
			
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return id;
	}
	
	public boolean check_exp(long id, long exp_date) {
		
		boolean exp_status = false;
		List<Object> data = null;
		
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM session WHERE id = :id AND expiration_date > :exp");
			
			Query query = entityManager.createNativeQuery(sql.toString(), SessionDto.class);
			query.setParameter("id", id);
			query.setParameter("exp", exp_date);
			
			data = query.getResultList();
	
			if(data.size() > 0) {
				exp_status = true;
			}
			
			return exp_status;
		
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			return exp_status;

		}
	}
}
