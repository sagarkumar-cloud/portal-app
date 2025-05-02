package in.sagar.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import in.sagar.dto.LoginDto;
import in.sagar.dto.RegisterDto;
import in.sagar.entity.Counsellor;
import in.sagar.repo.CounsellorRepo;
import in.sagar.service.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	private CounsellorRepo counsellorRepo;
	
	public CounsellorServiceImpl(CounsellorRepo counsellorRepo) {
		this.counsellorRepo = counsellorRepo;
	}

	@Override
	public Counsellor login(LoginDto loginDto) {
	Optional<Counsellor> counsellor=counsellorRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
	  if(counsellor.isPresent()) {
		return counsellor.get();
	  } 
		return null;
	}

	@Override
	public boolean register(RegisterDto registerDto) {
		Counsellor counsellor= new Counsellor();
		BeanUtils.copyProperties(registerDto, counsellor);
		Counsellor save = counsellorRepo.save(counsellor);
		return save.getCounsellorId()!=null;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		Optional<Counsellor> byEmail = counsellorRepo.findByEmail(email);
		if(byEmail.isPresent()) {
			return false;
		}
		return true;
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
