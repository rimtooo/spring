package com.yedam.app.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

// 특정 Controller가 아니라 모든 Controller에 적용하고 싶은게 있으면 아래의 어노테이션을 사용하면 된다.
@ControllerAdvice
public class WebAdvice {
	// 1. 예외처리
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<String> invokeError(IllegalAccessException error){ // 예외에 정보를 받고 싶다면
		return new ResponseEntity<>("Error Message", HttpStatus.BAD_REQUEST);
	}// end invokeError
	
	// 2. 공통된 변수 선언
	@ModelAttribute("contextPath") // 2) 화면에서 확인할 수 있게 해준다.
	public String contextPath(final HttpServletRequest request) {
		return request.getContextPath(); // 1) 리턴되는 결과값을 2) 화면에서 확인할 수 있게 해준다. 
	}// end contextPath
	
}// end class
