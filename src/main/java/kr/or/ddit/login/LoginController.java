package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;


@RequestMapping("login")
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);	

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(path="view", method= {RequestMethod.GET})
	public String view(Model model) {

		return "login";
	}
	
// String userid = req.getParameter("userid");
// String pass = req.getParameter("pass");
	//ioc 값 주입할때, 숫자도 가능했었음! 
//	@RequestMapping("process")
	public String process(String userid, String pass, int price) {
		//(1) 개별 인자로 갖는 방법
		logger.debug("userid : {} " , userid);
		logger.debug("pass : {} " , pass);
//		logger.debug("price: {} " , price);
		
		return "";
	}
	
	@RequestMapping(path="process", method= {RequestMethod.POST})
	public String process(UserVo userVo, HttpSession session,
						  RedirectAttributes ra) {
		
		logger.debug("userVo : {} " , userVo);   //파라미터 바인딩이 잘 되었는지 확인하기 위한 로그작업
				
		UserVo dbUser = userService.selectUser(userVo.getUserid());
		
		if(dbUser != null && userVo.getPass().equals(dbUser.getPass())) {  //파라미터로 들어온 비밀번호가 동일해야함

			 session.setAttribute("S_USER", dbUser); 
			 
			 return "main";   //forward
		}
		else {
			
			//내부적으로 session을 사용해 속성저장
			// 리다이렉트 처리가 완료되면 스프링 프레임워크에서 자동으로 session에서 제거해준다.
			ra.addFlashAttribute("msg", "잘못된 사용자 정보입니다.");
			
			//일반 속성을 추가한 경우 : addAttribute
			//리다이렉트 페이지의 파라미터로 전달된다.
//			ra.addAttribute("userid", "brown");
			
//			session.setAttribute("msg", "잘못된 사용자 정보입니다.");
			
	         return "redirect:/login/view";  //redirect

		}
	}
}
