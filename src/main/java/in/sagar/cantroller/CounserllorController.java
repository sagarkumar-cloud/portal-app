package in.sagar.cantroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sagar.dto.DashboardDto;
import in.sagar.dto.LoginDto;
import in.sagar.dto.RegisterDto;
import in.sagar.entity.Counsellor;
import in.sagar.service.CounsellorService;
import in.sagar.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounserllorController {

	private CounsellorService counsellorService;
	private EnquiryService enquiryService;

	public CounserllorController(CounsellorService counsellorService, EnquiryService enquiryService) {
		this.counsellorService = counsellorService;
		this.enquiryService = enquiryService;
	}

	@GetMapping("/")
	public String login(Model model) {
		LoginDto loginDto = new LoginDto();
		model.addAttribute("loginDto", loginDto);
		return "index";
	}

	@PostMapping("/login")
	public String handleLogin(LoginDto loginDto, Model model, HttpServletRequest request) {
		Counsellor login = counsellorService.login(loginDto);
		if (login == null) {
			model.addAttribute("emsg", "Invalid Credential.!");
			return "index";
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("COUNSELLOR_ID", login.getCounsellorId());
			return "redirect:dashboard";
		}
	}

	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		Integer cId = (Integer) session.getAttribute("COUNSELLOR_ID");

		DashboardDto dashboard = enquiryService.dashboard(cId);
		model.addAttribute("dashboardData", dashboard);

		return "dashboard";
	}

	@GetMapping("/register")
	public String register(Model model) {
		RegisterDto registerDto = new RegisterDto();
		model.addAttribute("registerDto", registerDto);
		return "register-page";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute RegisterDto registerDto, Model model) {
		boolean isEmail = counsellorService.isEmailAvailable(registerDto.getEmail());
		if (isEmail) {
			boolean register = counsellorService.register(registerDto);
			if (register) {
				model.addAttribute("smsg", "Register success.");
				model.addAttribute("registerDto",new RegisterDto());
			} else {
				model.addAttribute("emsg", "Register Failed..!!");
			}
		} else {
			model.addAttribute("emsg", "Duplicate Email.....!!");
		}
		return "register-page";
	}
	
	@GetMapping("/logout")
	public String logout(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
}
