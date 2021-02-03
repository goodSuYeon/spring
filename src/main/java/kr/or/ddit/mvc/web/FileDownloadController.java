package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("file")
@Controller
public class FileDownloadController {

	@Resource(name="userService")
	private UserService userService;
	
	//파일 요청을  처리해줄 메서드
	@RequestMapping("/fileDownloadView")
	public String fileDownloadView(String userid, Model model) {
		
		//1. 다운로드 파일의 경로 -> realFilename
		//2. 다운로드시 보여줄 파일명 -> filename
		//1,2를 model에 넣어준다
		//userid  파라미터를 보낸다고 가정하고, 해당 파라미터를 이용해 사용자으 사진정보(realFilename, filename)을 조회한다
		
		UserVo userVo = userService.selectUser(userid);
		
		model.addAttribute("realFilename", userVo.getRealfilename());
		model.addAttribute("filename", userVo.getFilename());
		
		return "fd";
	}
	
	@RequestMapping("fileDownload")
	public void fileDownload(HttpServletResponse resp , String userid){
		
		UserVo userVo = userService.selectUser(userid);
		// d:\\upload\\sally.png
		String realFilename = userVo.getRealfilename();
		String filename = userVo.getFilename();
				
		resp.setHeader("Content-Disposition", "attachement; file=" + filename);
		
		ServletOutputStream sos;
		
		try {
			sos = resp.getOutputStream();
			FileInputStream fis = new FileInputStream(new File("realFilename"));
			
			byte[] buf = new byte[512];   //byte배열 생성
			
			while(fis.read(buf) != -1) {
				sos.write(buf); 
			}
			fis.close();
			sos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
