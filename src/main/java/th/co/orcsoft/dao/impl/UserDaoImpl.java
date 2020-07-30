package th.co.orcsoft.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dao.IUserDao;
import th.co.orcsoft.dto.UserDto;

@Repository
public class UserDaoImpl {
	
//	@Autowired
//	private EntityManager entityManager;
//	
//	@Autowired
//	private IUserDao iuserdao;
	
//	public boolean getUserByUserName(UserDto userdto) {
//		
//		List<UserDto> list = null;
//		boolean bool = false;
//		StringBuilder sql =  new StringBuilder();
//		sql.append("SELECT * FROM rest_user WHERE username = :username and password = :password");
//
//		Query query = entityManager.createNativeQuery(sql.toString(), UserDto.class);
//		query.setParameter("username", userdto.getUsername());
//		query.setParameter("password", userdto.getPassword());
//		
//		list = query.getResultList();
//		
//		if(list.size() == 1) {
//			bool = true;
//		}
//		
//		return bool;
//	}
//	
//	public  List<UserDto> getUserByName() {
//		
//		return iuserdao.findByUsernameAndPassword("admin", "admin");
//		
//	}
}
