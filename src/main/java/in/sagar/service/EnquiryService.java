package in.sagar.service;

import java.util.List;

import in.sagar.dto.DashboardDto;
import in.sagar.dto.EnquiryDto;

public interface EnquiryService {

    DashboardDto dashboard(Integer counsellorId);
	
	boolean addEnquiry(EnquiryDto enquiryDto,Integer counsellorId);
	
	List<EnquiryDto> getAllEnquiry(Integer counsellorId);
	
	List<EnquiryDto> filterEnquiry(EnquiryDto enquiryDto,Integer counsellorId);
	
	public EnquiryDto getEnquiry(Integer enqid);
}
