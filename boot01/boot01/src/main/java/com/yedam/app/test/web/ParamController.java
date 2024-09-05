package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

//어노테이션 등록하는 위치
@Controller //Bean 등록, Web과 관련된 부분 담당
public class ParamController {
	// 1. content-type : application/x-www-form-urlencoded
	// QueryString(질의문자열) : key=value&key=value&... 형태로 값을 준다
	// Method : 상관없음 -> Default 타입이며 그외는 POST로 해야한다
	
	// QueryString => 커맨드 객체 (어노테이션 X, 	객체) => 어노테이션을 사용하지 않은 객체의 형태
	// Get, Post를 둘다 사용 가능하기 때문에 RequestMapping 를 사용한다.
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody // => AJAX 용 순수하게 데이터만 반환하는 컨트롤러이다 라는 의미
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}// end commandObject
	

	@RequestMapping(path="reqparm", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody // => AJAX 용 순수하게 데이터만 반환하는 컨트롤러이다 라는 의미
	public String requestParam(
			@RequestParam Integer employeeId, // 필수 
											String lastName, // 생략가능
			@RequestParam(name="message",  // name을 사용하면 무조건 이 이름(message)로 넘어와야한다.
											defaultValue="No message", 
											required = true) String msg
			) {
		String result = "";
		result += "Path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}// end requestParam
	
	
	// 2. Content-type : application/json
	// 사용할 수 있는 포멧은 JSON 포멧 : @RequsetBody, 배열 OR 객체만 지원 => Request안에 있는 Body를 컨트롤 한다.
	// Method는 무조건 POST(혹은 PUT) 왜냐하면 Body에 있는 값을 처리 하기 때문에
	// 객체로 하는것
	@PostMapping("resbody")
	@ResponseBody // => AJAX 용 순수하게 데이터만 반환하는 컨트롤러이다 라는 의미
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "";
		result += "Path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}// end requestBody
	
	// 배열로 받기
	@PostMapping("resbodyList")
	@ResponseBody // => AJAX 용 순수하게 데이터만 반환하는 컨트롤러이다 라는 의미
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "";
		result += "Path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}// end requestBodyList
	
	// 3. PathVariable : 경로의 값을 포함하는 방식, 단일 값 => VO는 못사용한다고 생각하면 된다.
	// Method는 상관없음
	// Content-typep도 상관없음
	// 경로처럼 인식하기 때문에
	@RequestMapping("path/{id}") // { } 위치에 있는 데이터를 id 라는 이름으로 받겠다 라는 의미 => warning 의미는 스프링부트에서 해당 부분을 다른걸로 변경해서 사용하라고 권장하기 때문에 발생함
	@ResponseBody
	public String pathVariable(@PathVariable String id) { // 이때 이 id가 어떤건지 지정하는 위치
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	

}// end class
