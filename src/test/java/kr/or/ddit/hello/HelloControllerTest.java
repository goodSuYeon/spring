package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

/*
   JAVA - gui swing, awt, java fx, swt
*/

public class HelloControllerTest extends WebTestConfig {

    //@Resource(name="helloController")
	// 스프링 bean 중에서 대입이 가능한 타입의 스프링 bean을 주입한다.
	// 만약, 동일한 스프링 bean이 여러개 있을 경우 @Qulifier 어노테이션을 통해서
	// 특정 스프링 bean의 이름을 지정할 수 있다.
	// -> @Resource 어노테이션 하나를 사용했을 때와 동일하다.

	
	//localhost/hello/view
	
	@Test
	public void viewTest() throws Exception {

		MvcResult mvcResult =  mockMvc.perform(get("/hello/view"))
							   .andExpect(status().isOk())
							   .andExpect(view().name("hello")) 
							   .andExpect(model().attributeExists("userVo"))
							   .andDo(print())
							   .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("hello", mav.getViewName());
		
		UserVo userVo = (UserVo)mav.getModel().get("userVo");  
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void pathVariableTest() throws Exception {
		
		MvcResult mvcResult =  mockMvc.perform(get("/hello/path/brown"))
				   .andExpect(status().isOk()) 
				   .andExpect(model().attributeExists("subpath"))
				   .andDo(print())
				   .andReturn();
	}
	
}
