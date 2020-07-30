package th.co.orcsoft.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.MenuDto;

@Repository
public interface IMenuDao extends CrudRepository<MenuDto, Integer> {
	
	MenuDto findById(int id);
	
	MenuDto findByName(String name);
	
	@Query("FROM MenuDto m WHERE m.name LIKE concat('%',:keyword,'%') OR m.description LIKE concat('%',:keyword,'%') OR m.types LIKE concat('%',:keyword,'%')")
	List<MenuDto> findByNameOrDescriptionOrTypes(@Param ("keyword") String keyword);
	
}
