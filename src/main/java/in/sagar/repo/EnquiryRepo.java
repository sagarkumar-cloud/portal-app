package in.sagar.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.sagar.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	@Query(value = "select * from enquiry_tbl where counsellor_id= :counsellorId", nativeQuery = true)
	List<Enquiry> fetchEnqsWithCounsellorId(Integer counsellorId);
	
	//we can also use this method to retrieve all enquiry
	List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);
}
