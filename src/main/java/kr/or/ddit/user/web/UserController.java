package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.validator.UserVoValidator;

@Controller
@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
			
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@RequestMapping(path="allUser")
	public String allUser(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());
		
		return "/user/allUser";	
	}
	
	@RequestMapping("pagingUser")
	public String paginfUser(@RequestParam(defaultValue = "1") int page, 
						     @RequestParam(defaultValue = "5") int pageSize,
						     Model model) {
		
		logger.debug("page: {} , pageSize: {}", page, pageSize);
		
		PageVo pageVo = new PageVo(page,pageSize);   //생성자 인자 사용
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
	   
		return "/user/pagingUser";	
	}
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page, 
						     @RequestParam(defaultValue = "5") int pageSize,
						     Model model) {

		PageVo pageVo = new PageVo(page,pageSize);   
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
	   
		//tiles-definition에서 설정한 name
		return "tiles.user.pagingUser";	
	}
	
	//사용자 리스트가 없는 상태의 화면만 응답(데이터x)
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
		
		return "tiles.user.pagingUserAjax";  //view name 리턴
	}
	
	@RequestMapping("pagingUserAjax")
	public String pagingUserAjax(@RequestParam(defaultValue = "1") int page, 
						     @RequestParam(defaultValue = "5") int pageSize,
						     Model model) {

		PageVo pageVo = new PageVo(page,pageSize);   
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
	   
		//우리가 설정한 jsonView 리턴
		return "jsonView";	
	}
	
	@RequestMapping("pagingUserAjaxHtml")
	public String pagingUserAjaxHtml(@RequestParam(defaultValue = "1") int page, 
						     @RequestParam(defaultValue = "5") int pageSize,
						     Model model) {

		PageVo pageVo = new PageVo(page,pageSize);   
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
	   
		return "user/pagingUserAjaxHtml";	
	}
	
	@RequestMapping(path="allUserTiles")
	public String allUserTiles(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());
		
		return "tiles.user.allUser";
	}

	/*
	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {
		logger.debug("pageVo: {}", pageVo);
		
		return "";
	}
	*/
	
	//사용자 등록
	@RequestMapping(path="registUser", method=RequestMethod.GET) 
	public String registUser(){
		
//		return "user/registUser";
		return "tiles.user.registUser";	
	}

	@RequestMapping(path="registUser", method=RequestMethod.POST)  
	public String registUser(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model){
		
//		new UserVoValidator().validate(userVo, result);
		
		//에러 검증
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}
		
		int resultCnt = 0;
		
		if(profile.getSize() > 0) {
			String originalFilename = profile.getOriginalFilename();
			
			//UUID는 범용 고유 식별자로써, 2f48f241-9d64-...와같은 형태로 고유한 값을 생성
			//겹칠일이 거의 없음! 그래서 테이블의 key값이나 파일 업로드시 임시 파일명 등에 쓰임
			String filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			userVo.setFilename(filename);
			userVo.setRealfilename("d:\\upload\\" + filename);
			
			//파일을 업로드하면 transferTo 를 통해서 실제 파일시스템에 쓰게(write)됨
			try {
				profile.transferTo(new File(userVo.getFilename()));
				resultCnt = userService.registerUser(userVo);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultCnt == 1) {
			return "redirect:/user/pagingUser";
		} else {
			return "user/registUser";
		}
	}
	
	//사용자 상세조회
	@RequestMapping(path="user", method=RequestMethod.GET)
	public String user(Model model, String userid){
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/user";
	}
	
	//사용자 삭제	
	@RequestMapping(path="deleteUser", method=RequestMethod.POST)
	public String deleteUser(Model model, String userid){
		
		int deleteCnt = 0;
		try {
			deleteCnt = userService.deleteUser(userid);
		} catch (Exception e) {
			deleteCnt = -1;
		}
		if (deleteCnt == 1) {
			return "redirect:/user/pagingUser";
		} else {
			return "redirect:/user/user?userid=" + userid;
		}
	}
	
	//사용자 수정	
	@RequestMapping(path="modifyUser", method=RequestMethod.GET)
	public String modifyUser(String userid, Model model) {

		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/userModify";	
	}
	
	@RequestMapping(path="modifyUser", method=RequestMethod.POST)
	public String modifyUser(UserVo userVo, MultipartFile profile, 
							  RedirectAttributes ra, Model model) {
		//RedirectAttributes : 리다이렉트가 발생하기 전에 모든 플래시 속성을 세션에 복사 (URL에만 노출안될뿐.)
		int updateCnt = 0;

		if (profile.getSize() > 0) {
				String originalFilename = profile.getOriginalFilename();
				String filename = UUID.randomUUID().toString() + "."
						+ originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

				userVo.setFilename(originalFilename);
				userVo.setRealfilename("d:\\upload\\" + filename);

				try {
					profile.transferTo(new File(userVo.getRealfilename()));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			updateCnt = userService.modifyUser(userVo);
	      
	      if(updateCnt == 1) {	 
	    	  ra.addAttribute("userid", userVo.getUserid());
	    	  return "redirect:/user/user";

			} else {

				return modifyUser(userVo.getUserid(), model);
			}
		}
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");

		UserVo userVo = userService.selectUser(userid);

		String path = "";
		if (userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		} else {
			path = userVo.getRealfilename();
		}
		
		logger.debug("path :{}" , path );
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			byte[] buff = new byte[512];
			
			while (fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@RequestMapping("profileDownload")
	public void profileDownload(HttpServletResponse rep,
								HttpServletRequest req, String userid) {

		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		String filename = "";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
			filename = "unknown.png";
		}else {
			path = "d:\\upload\\"+userVo.getRealfilename();
			filename = userVo.getFilename();
		}
		
		rep.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = rep.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
  }
