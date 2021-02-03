package kr.or.ddit.file;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.config.WebTestConfig;


public class FileuploadTest extends WebTestConfig {

	@Test
	public void fileuploadViewTest() throws Exception {
		mockMvc.perform(get("/mvc/fileupload/view"))
				.andExpect(view().name("file/view"))
				.andDo(print());
	}
	
	@Test
	public void fileuploadTest() throws Exception {
		
		ClassPathResource resourece = new ClassPathResource("kr/or/ddit/upload/po.png");
		
		MockMultipartFile file = new MockMultipartFile("picture", "po.png", "image/png", resourece.getInputStream());
		
		mockMvc.perform(fileUpload("/mvc/fileupload/upload")
			   .file(file)
			   .param("userid", "suyeon"))
			   .andExpect(view().name("file/view"))
			   .andDo(print());
	}

}
