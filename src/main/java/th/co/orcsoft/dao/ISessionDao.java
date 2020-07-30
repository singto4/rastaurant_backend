package th.co.orcsoft.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.co.orcsoft.dto.SessionDto;


@Repository
public interface ISessionDao extends CrudRepository<SessionDto, Long> {
	
	@Query("SELECT MAX(s.id) FROM SessionDto s ")
	long findMaxId();
}
