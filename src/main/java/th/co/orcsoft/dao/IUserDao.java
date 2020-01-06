package th.co.orcsoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.UserDto;

@Repository
public interface IUserDao extends CrudRepository<UserDto, Long> {

}
