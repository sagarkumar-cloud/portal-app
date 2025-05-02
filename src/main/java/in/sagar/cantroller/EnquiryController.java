package in.sagar.cantroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sagar.dto.EnquiryDto;
import in.sagar.service.CounsellorService;
import in.sagar.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	private EnquiryService enquiryService;
	public EnquiryController(EnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}
	
	
	@GetMapping("/addEnq")
	public String addEnquiry(Model model) {
		EnquiryDto enquiryDto = new EnquiryDto();
		model.addAttribute("enquiryDto", enquiryDto);
		return "add-enquiry";
	}

	@PostMapping("/addEnquiry")
	public String handleAddEnquiry(EnquiryDto enquiryDto, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("COUNSELLOR_ID");
		boolean enquiry = enquiryService.addEnquiry(enquiryDto, counsellorId);
		if (enquiry) {
			model.addAttribute("smsg", "Enquiry Addedd..");
			model.addAttribute("enquiryDto", new EnquiryDto());
		} else {
			model.addAttribute("emsg", "Faild to add enquiry.!");
			model.addAttribute("enquiryDto", enquiryDto);
		}
		return "add-enquiry";
	}
	
	@GetMapping("/enquiryEdit")
	public String editEnquiry(@RequestParam("id") Integer enqId, Model model) {
	    EnquiryDto enquiryDto = enquiryService.getEnquiry(enqId);
	    model.addAttribute("enquiryDto", enquiryDto);
	    return "add-enquiry";
	}
	

	@GetMapping("/viewEnq")
	public String viewEnquiry(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("COUNSELLOR_ID");
		List<EnquiryDto> allEnquiry = enquiryService.getAllEnquiry(counsellorId);
		
		model.addAttribute("allEnq", allEnquiry);
		
		EnquiryDto enqDto = new EnquiryDto();
		model.addAttribute("enquiryDto", enqDto);
		
		return "view-enquiry";
	}


	@PostMapping("/filterEnq")
	public String handleViewEnquiry(@ModelAttribute("enquiryDto") EnquiryDto enquiryDto, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("COUNSELLOR_ID");
		List<EnquiryDto> allEnquiry = enquiryService.filterEnquiry(enquiryDto, counsellorId);
		model.addAttribute("allEnq", allEnquiry);
		model.addAttribute("enquiryDto", new EnquiryDto());
		return "view-enquiry";
	}

	
	
}
