package in.sagar.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.sagar.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

	Optional<Counsellor> findByEmailAndPassword(String email, String password);

	
	//@Query(value = "SELECT * FROM counsellor_tbl WHERE email = :email", nativeQuery = true)
	//Optional<Counsellor> emailCheck(String email);

    public Optional<Counsellor> findByEmail(String email);
}
