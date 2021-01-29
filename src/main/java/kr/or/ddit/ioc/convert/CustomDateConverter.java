package kr.or.ddit.ioc.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date> {    //S는 소스 T는 반환할 타입

	private String dateFormat;     //xml에 설정한 값을 쓴다 필드지정(즉, set주입)
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}


	//source : 
	@Override
	public Date convert(String source) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		Date date = null;  //지역변수
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		return date;
	}  

}
