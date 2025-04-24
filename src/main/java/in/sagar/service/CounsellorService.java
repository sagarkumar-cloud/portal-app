package in.sagar.service;

import in.sagar.dto.LoginDto;
import in.sagar.dto.RegisterDto;
import in.sagar.entity.Counsellor;

public interface CounsellorService {
	
	Counsellor login(LoginDto loginDto);

	boolean register(RegisterDto registerDto);
	
	boolean isEmailAvailable(String email);
}
