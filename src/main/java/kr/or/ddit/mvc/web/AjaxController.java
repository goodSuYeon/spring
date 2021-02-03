package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	
	//@RequestMapping이 붙은 메서드를 먼저 실행하기 전에, @ModelAttribute를 먼저 실행한다
	//model에 자동으로 넣어준다
	@ModelAttribute(name="rangers")   //name 지정
	public List<String> rangers(){
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("moon");
		rangers.add("james");
		
		return rangers;   //name을 반환	
	}
	
	//localhost/ajax/jsonView
	//그러니 여기서는 model객체를 사용 할 필요없음!
	@RequestMapping("jsonView") 
	public String jsonView() {

		return "jsonView";   		
	}
	
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewObj() {
		return new MappingJackson2JsonView();
	}
	
	@RequestMapping("jsoinViewMav")
	public ModelAndView jsoinViewMav() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("jsoinView");
		
		return mav;
	}
	
	
}
