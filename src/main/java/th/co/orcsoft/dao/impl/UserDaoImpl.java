package th.co.orcsoft.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.UserDto;

@Repository
public class UserDaoImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	public UserDto getUserByUserName(String userName) {
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from REST_USER where USERNAME = :userName");

		Query query = entityManager.createNativeQuery(sql.toString(), UserDto.class);
		query.setParameter("userName", userName);
		return (UserDto) query.getSingleResult();
	}
}
