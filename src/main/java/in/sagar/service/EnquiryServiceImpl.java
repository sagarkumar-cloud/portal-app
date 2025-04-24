package in.sagar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import in.sagar.dto.DashboardDto;
import in.sagar.dto.EnquiryDto;
import in.sagar.entity.Counsellor;
import in.sagar.entity.Enquiry;
import in.sagar.repo.CounsellorRepo;
import in.sagar.repo.EnquiryRepo;

public class EnquiryServiceImpl implements EnquiryService{
	
	private EnquiryRepo enquiryRepo;
	private CounsellorRepo counsellorRepo;
	
    public EnquiryServiceImpl(EnquiryRepo enquiryRepo,CounsellorRepo counsellorRepo) {
		this.enquiryRepo = enquiryRepo;
		this.counsellorRepo=counsellorRepo;
	}

	@Override
	public DashboardDto dashboard(Integer counsellorId) {
		List<Enquiry> allEnq = enquiryRepo.getAllEnqByCounsellorId(counsellorId);
		
		int totalEnq =(int) allEnq.stream().count();
		int lostEnq = (int)allEnq.stream().filter(enq->enq.getStatus().equals("Lost")).count();
		int enrolledEnq = (int)allEnq.stream().filter(enq->"Enrolled".equals(enq.getStatus())).count();
		int openEnq=totalEnq - (lostEnq+enrolledEnq);
		
		DashboardDto dashboard=new DashboardDto();
		dashboard.setEnrolledEnquiry(enrolledEnq);
		dashboard.setLostEnquiry(lostEnq);
		dashboard.setOpenEnquiry(openEnq);
		dashboard.setTotalEnquiry(totalEnq);
		return dashboard;
	}

	@Override
	public boolean addEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {
		Counsellor counsellor = counsellorRepo.findById(counsellorId).get();
		if(counsellor.getCounsellorId() != null) {
			Enquiry enq=new Enquiry();
			BeanUtils.copyProperties(enquiryDto,enq);
			enq.setCounsellor(counsellor);
			enquiryRepo.save(enq);
			return true;
		}
		return false;
	}

	@Override
	public List<EnquiryDto> getAllEnquiry(Integer counsellorId) {
		List<Enquiry> allEnq = enquiryRepo.getAllEnqByCounsellorId(counsellorId);
		
		List<EnquiryDto> enqDto=new ArrayList<>();
		for(Enquiry enq:allEnq) {
			EnquiryDto enquiryDto = new EnquiryDto();
			BeanUtils.copyProperties(enq,enquiryDto );
			enqDto.add(enquiryDto);
		}
	
		return enqDto;
	}

	@Override
	public List<EnquiryDto> filterEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryDto getEnquiry(Integer enqid) {
		// TODO Auto-generated method stub
		return null;
	}

}
