package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;


public class FileDownloadView extends AbstractView {

	//응답을 만들어 내는 부분		
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// d:\\upload\\sally.png
		String realFilename = (String) model.get("realFilename");
		String filename = (String) model.get("filename");
		
		response.setHeader("Content-Disposition", "attachement; file=" + filename);
		
		FileInputStream fis = new FileInputStream(new File("realFilename"));
		
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buf = new byte[512];   //byte배열 생성
		
		while(fis.read(buf) != -1) {
			sos.write(buf); 
		}
		fis.close();
		sos.close();
	}
}