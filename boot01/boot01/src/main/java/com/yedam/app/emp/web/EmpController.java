package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller // 역할은 라우터를 등록하는 역할을 맡는다
// URI + METHOD => Service => View
@RequiredArgsConstructor // LomBok이 제공하는 어노테이션이며 final을 가지는 필드를 골라서 매개변수로 갖게되는 생성자를 만들어 준다.
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스
	private final EmpService empService;
	
	/*
	 @Autowired
	 EmpController(EmpService empService){
	 	this.empService = empService;
	 }
	 */
	
	// GET : 조회, 빈페이지
	// POST : 데이터 조작(등록, 수정, 삭제) -> 삭제의 경우는 Primary Key로 특정지어 삭제할 경우에는 Get 써도 무방하다
	
	// 전체조회 : GET
	@GetMapping("empList")
	public String empList(Model model) { // Model Spring 쪽 Model 임포트해야한다.
										// Model = Request + Response
		// 1) 기능 수행 => Service
		List<EmpVO> list = empService.empList();
		
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list); // 이부분이 우리가 여는 페이지에 데이터가 담겨 있는것
		
		return "emp/list"; // 3) 데이터를 출력할 페이지 결정 // 리턴에는 /로 시작을 하면 안된다.
		// prefix + return + suffix => /ViewResolver
	}// end empList
	
	// 단건조회 : Get => QueryString, employeeId  커맨더 객체로 처리 (Get 방식이기 때문에 커맨드 객체 OR @RequestParam)
	@GetMapping("empInfo") //empInfo?key=value
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/info";
	}// end empInfo
	
	// 등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}// end empInsertForm
	
	// 등록 - 처리(기능) : POST
	// 등록하는 화면에 따라 결정해야한다.
	// 우리가 흔히 사용하는 등록하는 화면에서는 Form태그를 통한 submit(페이지) 방식이다
	// 																	=> QueryString (커맨드 객체) : 한명만 등록하는 방식이기 때문에 커맨드 객체를 사용하는게 좋다
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) { 
			// 정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId="+eid; // redirect 는 페이지가 아니고 경로
			// "redirect: "가 가능한 경우는 GetMapping 만 가능함.
		} else {
			// 등록되지 않은 경우
			url = "redirect:empList";
		}
		
		return url;
	}// end empInsertProcess
		
	// 수정 - 페이지 > 결국에는 단건조회와 비슷하기때문에 겸용으로 하면 한개는 줄어 들 수 있다. : GET , 조건이 필요 <-> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}// end empUpdateForm
	
	// 수정처리 할 때 2가지 경우를 다 해보겠다. 
	// 수정 - 처리(기능) : AJAX => QueryString : POST
	@PostMapping("empUpdate")
	//반환하는 리턴타입에 대해 달라졌다라는 의미 // 응답이기 때문에 리턴하기 전에 해야한다.
	@ResponseBody // AJAX 
	public Map<String, Object> empUpdateAJAXQueryString(EmpVO empVO){
		return empService.empUpdate(empVO); // 실제 Data가 리턴된다
	}// end empUpdateAJAXQueryString
	
	// 수정 - 처리(기능) : AJAX => JSON (@RequestBody) : POST
	//@PostMapping("empUpdate") 
	/*
	 * {POST [/empUpdate]}: There is already 'empController' bean method 이렇게 발생하는 경우는 위에 어노테이션이 중복이기 때문에 발생한다.
	 * 
	 * */
	//반환하는 리턴타입에 대해 달라졌다라는 의미 // 응답이기 때문에 리턴하기 전에 해야한다.
	@ResponseBody // AJAX
	public Map<String, Object> empUpdateAJAXJSON(@RequestBody EmpVO empVO){ // 받는걸 JSON으로 받겠다라는 의미
		return empService.empUpdate(empVO); // 실제 Data가 리턴된다
	}// end empUpdateAJAXJSON
	
	
	// 삭제 - 처리(기능) : Get => QueryString( @RequestParam) 
	// 삭제는 의도적으로 redirect: 를 시켜서 사용자가 삭제를 한 부분에 적용된 부분을 보게 하는게 좋다.
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList"; // 단건조회 페이지는 이미 삭제된 정보이기 때문에 전체조회밖에 할 수 없다.
	}// end empDelete
	
}// end class
