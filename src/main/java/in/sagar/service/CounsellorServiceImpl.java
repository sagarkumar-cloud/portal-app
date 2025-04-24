package in.sagar.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import in.sagar.dto.LoginDto;
import in.sagar.dto.RegisterDto;
import in.sagar.entity.Counsellor;
import in.sagar.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	private CounsellorRepo counsellorRepo;
	
	public CounsellorServiceImpl(CounsellorRepo counsellorRepo) {
		this.counsellorRepo = counsellorRepo;
	}

	@Override
	public Counsellor login(LoginDto loginDto) {
	Counsellor counsellor=counsellorRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()).get();
	  if(counsellor.getCounsellorId() != null) {
		  return counsellor;
	  } 
		return null;
	}

	@Override
	public boolean register(RegisterDto registerDto) {
		Counsellor coun=mapToCounsellor(registerDto);
		if(! isEmailAvailable(registerDto.getEmail())) {
			counsellorRepo.save(coun);
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		Optional<Counsellor> byEmail = counsellorRepo.findByEmail(email);
		if(byEmail.isPresent()) {
			return true;
		}
		return false;
	}
	
	public Counsellor mapToCounsellor(RegisterDto registerDto) {
		Counsellor counsellor = new Counsellor();
		counsellor.setEmail(registerDto.getEmail());
		counsellor.setName(registerDto.getName());
		counsellor.setPassword(registerDto.getPassword());
		counsellor.setPhno(registerDto.getPhno());
		return counsellor;
	}

}
