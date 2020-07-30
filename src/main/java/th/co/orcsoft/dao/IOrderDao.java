package th.co.orcsoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.co.orcsoft.dto.OrderDto;

public interface IOrderDao extends CrudRepository<OrderDto, Integer> {

	List<OrderDto> findAll();
	
	@Query("SELECT o.menu, SUM(o.quantity) AS sum FROM OrderDto o "
			+ "INNER JOIN BillStatusDto b ON o.create_time = b.time "
			+ "WHERE o.time LIKE concat(:date,'%') AND b.status = 'done' "
			+ "GROUP BY o.menu "
			+ "ORDER BY SUM(o.quantity) DESC, o.menu")
	List<Object[]> findAllMenuReport(@Param("date") String date);
	
	@Query("SELECT o.bill, b.time, m.price FROM OrderDto o "
			+"INNER JOIN BillStatusDto b ON o.create_time = b.time "
			+"INNER JOIN MenuDto m ON o.menu = m.name "
			+"WHERE b.time LIKE concat(:date,'%') "
			+"AND b.status = 'done' "
			+"ORDER BY o.bill, b.time")
	List<Object[]> findAllBillReport(@Param("date") String date);
}
