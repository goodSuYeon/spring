package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//우리가 구현한 로직이 올바른지 검증할 때 여기서 작성!
		//command객체마다 검증 필요
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {  //검증 대상 객체,에러인자타입(우리가 에러 작성)
		
		// 우리가 검증하고 싶은 검증로직을 작성!
		//에러로 판단되는 상황을 체크하여 errors에 추가
		UserVo userVo = (UserVo) target;
		
		//userid 길이가 5글자 이상으로 설정
		if(userVo.getUserid().length() < 5) {   //userid 길이가 5글자보다 작다면
			errors.rejectValue("userid", "length");
		}
		
	}

}
