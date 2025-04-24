package in.sagar.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sagar.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer>{
	
	Optional<Counsellor> findByEmailAndPassword(String email,String password);

	Optional<Counsellor> findByEmail(String email);
	
	
}
