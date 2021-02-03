package kr.or.ddit.login;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;


public class LoginControllerTest extends WebTestConfig {

	@Test
	public void viewTest() throws Exception {
		//분리 한 이유 -> 편하게 쓰기위해
		mockMvc.perform(get("/login/view"))  	
		   .andExpect(status().isOk()) 		    
		   .andExpect(view().name("login"));
	}
	
	//메서드 인자로 파라미터 값을 두개 보냈을 때, 바인딩 되는가? 를 확인하기 위한 테스트
	@Test
	public void processSucessTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid", "brown")
			    .param("pass", "brownPass"))  
			    .andExpect(view().name("main"))
			    .andDo(print());   //성공하는 케이스
									
	}
	
	//메서드 인자로 파라미터 값을 두개 보냈을 때, 바인딩 되는가? 를 확인하기 위한 테스트
	@Test
	public void processFailTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid", "brown")
			    .param("pass", "failPass"))  
			    .andExpect(view().name("redirect:/login/view"))   //redirect하는 응답 (view name이 이렇게 와야함) 
			    .andDo(print());   //실패하는 케이스				
	}
	
	@Test
	public void pagingUserTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser"))
				.andExpect(view().name("")) 
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pagination"))
				.andExpect(model().attributeExists("pagevo"))
			    .andDo(print());   			
	}
	
	@Test
	public void test() {
		PageVo pageVo = new PageVo();
		System.out.println("pageVo.getPage() : " + pageVo.getPage());
	}
}
