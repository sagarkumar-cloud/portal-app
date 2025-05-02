package in.sagar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.sagar.dto.DashboardDto;
import in.sagar.dto.EnquiryDto;
import in.sagar.entity.Counsellor;
import in.sagar.entity.Enquiry;
import in.sagar.repo.CounsellorRepo;
import in.sagar.repo.EnquiryRepo;
import in.sagar.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	private EnquiryRepo enquiryRepo;
	private CounsellorRepo counsellorRepo;

	public EnquiryServiceImpl(EnquiryRepo enquiryRepo, CounsellorRepo counsellorRepo) {
		this.enquiryRepo = enquiryRepo;
		this.counsellorRepo = counsellorRepo;
	}

	@Override
	public DashboardDto dashboard(Integer counsellorId) {
		
		//we can also use this method to retrieve all enquiry
          // List<Enquiry> allEnq = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		
		
		Counsellor coun= new Counsellor();
		coun.setCounsellorId(counsellorId);
		Enquiry enquiry = new Enquiry();
		 enquiry.setCounsellor(coun);
		 List<Enquiry> allEnq = enquiryRepo.findAll(Example.of(enquiry)); 
		
		int totalEnq = allEnq.size();
		int lostEnq = (int) allEnq.stream().filter(enq -> enq.getStatus().equals("Lost")).count();
		int enrolledEnq = (int) allEnq.stream().filter(enq -> "Enrolled".equals(enq.getStatus())).count();
		int openEnq = (int) allEnq.stream().filter(enq->enq.getStatus().equals("Open")).count();
		
		//allEnq.stream().filter(enq->enq.getClassMode().equals("online")).collect(Collectors.toList()).size();
		
		DashboardDto dashboard = new DashboardDto();
		dashboard.setEnrolledEnquiry(enrolledEnq);
		dashboard.setLostEnquiry(lostEnq);
		dashboard.setOpenEnquiry(openEnq);
		dashboard.setTotalEnquiry(totalEnq);
		return dashboard;
	}

	@Override
	public boolean addEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {
		
		Enquiry enq= new Enquiry();
		BeanUtils.copyProperties(enquiryDto, enq);
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		enq.setCounsellor(counsellor);
		Enquiry saveEnq = enquiryRepo.save(enq);
		return saveEnq.getEnquiryId()!=null;
	}

	@Override
	public List<EnquiryDto> getAllEnquiry(Integer counsellorId) {
		
		List<Enquiry> enqs = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		
		List<EnquiryDto> enqDto= new ArrayList<>();
		
		for(Enquiry enq:enqs) {
			EnquiryDto enqD= new EnquiryDto();
			BeanUtils.copyProperties(enq, enqD);
			enqDto.add(enqD);
		}
		return enqDto;
	}

	@Override
	public List<EnquiryDto> filterEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {
      //QBE
		 Enquiry enq= new Enquiry();
		 
		 if(enquiryDto.getClassMode() != null && !enquiryDto.getClassMode().equals("")) {
			 enq.setClassMode(enquiryDto.getClassMode());
		 }
		 if(enquiryDto.getCourse() != null && !enquiryDto.getCourse().equals("")) {
			 enq.setCourse(enquiryDto.getCourse());
		 }
		 if(enquiryDto.getStatus() != null && !enquiryDto.getStatus().equals("")) {
			 enq.setStatus(enquiryDto.getStatus());
		 }
		 Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		 enq.setCounsellor(counsellor);
		 
		 List<Enquiry> allEnq = enquiryRepo.findAll(Example.of(enq));
		 
		 List<EnquiryDto> enqDto=new ArrayList<>();
		 for(Enquiry enquiry :allEnq) {
			 EnquiryDto enqs = new EnquiryDto();
			 BeanUtils.copyProperties(enquiry,enqs );
			 enqDto.add(enqs);
		 }
		 return enqDto;
	}

	@Override
	public EnquiryDto getEnquiry(Integer enqid) {
		Enquiry enq = enquiryRepo.findById(enqid).orElseThrow();
		EnquiryDto enqDto= new EnquiryDto();
		BeanUtils.copyProperties(enq, enqDto);
		return enqDto;
	}

}
