package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UsersVo;

@RequestMapping("mvc")
@Controller
public class MvcController {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping("fileupload/view")
	public String fileuploadView() {
	
		return "file/view";
	}
	
	//파라미터는 userid, picture
	@RequestMapping("fileupload/upload")
	public String fileupload(String userid, MultipartFile picture) {
		
		logger.debug("userid: {}" , userid);
	
		logger.debug("filesize: {}, name: {}, originalFilename: {}",
					 picture.getSize(), picture.getName(), picture.getOriginalFilename());	
		
		//파일 객체생성, 경로생성
		try {
			picture.transferTo(new File("d:\\upload\\" + picture.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "file/view";
	}
	
	@RequestMapping("multi/view")
	public String multiView() {
		return "multi/view";
	}
	
//	@RequestMapping("multi/param")
	public String multiParam(String[] userid) {   //userid를 배열형태로!
		
		logger.debug("userid : {}" , (Object) userid);
		
		return "multi/view";
		
	}
	
	@RequestMapping("multi/param")
	public String multiParam(UsersVo usersVo) {   //UsersVo에 userid 생성해서 UserVo를 가져옴
		
		logger.debug("usersVo : {}" , (Object) usersVo);
		
		return "multi/view";
		
	}
}
