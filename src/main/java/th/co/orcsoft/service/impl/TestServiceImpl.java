package th.co.orcsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.orcsoft.dao.IUserDao;
import th.co.orcsoft.dao.impl.UserDaoImpl;
import th.co.orcsoft.dto.UserDto;

@Service
public class TestServiceImpl {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	public UserDto saveUser(String userName) {
		UserDto user = new UserDto();
		user.setUserName(userName);
		
		userDao.save(user);
		
		return user;
	}
	
	public UserDto getUserByUserName(String userName) {
		UserDto user = userDaoImpl.getUserByUserName(userName);
		return user;
	}

}
