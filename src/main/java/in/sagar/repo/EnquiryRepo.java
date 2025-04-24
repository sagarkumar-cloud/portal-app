package in.sagar.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.sagar.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{


	@Query("from Enquiry where counsellor_id= :counsellorId")
	List<Enquiry> getAllEnqByCounsellorId(Integer counsellorId);
}
