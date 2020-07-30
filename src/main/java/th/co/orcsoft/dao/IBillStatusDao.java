package th.co.orcsoft.dao;

import java.util.List;

//import javax.transaction.Transactional;

//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.co.orcsoft.dto.BillStatusDto;

public interface IBillStatusDao extends CrudRepository<BillStatusDto, Integer>{
	
	List<BillStatusDto> findByBillAndStatus(String bill, String status);
	
	BillStatusDto findById(int id);
	
	@Query("FROM BillStatusDto b ORDER BY b.status ASC, b.time ASC, b.update_time ASC")
	List<BillStatusDto> findAllByOrderByStatusAndTime();
	
	@Query(value = "SELECT order_bill.menu AS menu, order_bill.quantity AS quantity, menu.price FROM order_bill "
			+ "INNER JOIN bill_status ON order_bill.bill = bill_status.bill "
			+ "INNER JOIN menu ON order_bill.menu = menu.name "
			+ "WHERE order_bill.bill = :bill " 
			+ "AND order_bill.orderd_time >= bill_status.create_time "
			+ "AND bill_status.status = 'doing'", nativeQuery = true)
	List<Object[]> findByBill(@Param ("bill") String bill);
		
}
