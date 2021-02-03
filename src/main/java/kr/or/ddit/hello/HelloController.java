package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.user.service.UserService;

@SessionAttributes("rangers")
@RequestMapping("hello")
@Controller   //컨트롤러라는 어노테이션 생성
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	//@ModelAttribute 실습 (사용빈도는 적음)
	@ModelAttribute(name="rangers")
	public List<String> rangers(){
		
		logger.debug("helloController.rangers()");
		
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("james");
		list.add("cony");
		list.add("moon");
		return list;
	}
	
	
	// localhost/hello/view 요청
	@RequestMapping("view")   //  한번에 처리할 경우 @RequestMapping("hello/view")로도 가능함
	public String view(Model model,
					   @ModelAttribute(name="rangers") List<String> rangers ,
					   HttpServletRequest request) {
		
		logger.debug("HelloController.view() : {}" , userService.selectUser("brown"));
		logger.debug("rangers : {}" , rangers);
		
		//request.setAttribute("userVo", userService.getUser("brown")) 와 동일하다
		model.addAttribute("userVo", userService.selectUser("brown"));

		return "hello";
	}
	
	// hello/path/subPath
	// hello/path/brown
	// hello/path/cony ... hello/path/뒤에 뭐가 올지 딱 정해주지 않았으므로, 뭐가올지 모름!
	@RequestMapping("path/{subpath}")
	public String pathVariable(@PathVariable("subpath") String subpath , Model model,
							   @RequestHeader(value = "User-Agent") String UserAgent) {
		/*
		  User-Agent 헤더 값 바인딩
		*/
		model.addAttribute("subpath", subpath);
		
		
		//User-Agent 값 로거로 출력
		logger.debug("User-Agent: {}",UserAgent);
	
		return "hello";
		
	}
	
}
