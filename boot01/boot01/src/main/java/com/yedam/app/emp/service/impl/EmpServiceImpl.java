package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service // 서비스라는 대상을 등록하는 어노테이션 입력 => AOP이 적용가능한 Bean
public class EmpServiceImpl implements EmpService {
	// 이때 필드 주입은 하지말아야함
	private EmpMapper empMapper;
	
	@Autowired // 경고가 뜨는 이유는 생성자가 하나이기 때문에 된다.
	// 생성자가 여러개면 Autowired는 하는데 생성자가 하나이기 때문에 생성자가 자동으로 생성되기 때문에 경고를 표시한다 => 넣어도 되고 안 넣어도 상관은 없다.
	// 생성자로 Mapper을 주입한다
	EmpServiceImpl(EmpMapper empMapper){
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAllList();
	}// end empList

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}// end empInfo

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		// 정상적으로 등록이 되었으면 등록한 employee_id를 리턴해준다.
		// 삼항연산자는 반드시 변수가 필요하다. 
		// => 삼항연산자는 단독으로 사용할 수 없고 변수가 있을 경우에 쓴다. 
		// => 반드시 값을 돌려주는 조건문이기 때문에 두 가지 중 값을 1개만 돌려 준다면 삼항연산자가 편하다.
		return result == 1 ? empVO.getEmployeeId() : -1; // 이런 경우(둘 중에 값을 돌려주는 경우) 에는 삼항연산자를 사용하면 더 편하다.
	}// end empInsert

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) { // Map 기반은 보통 AJAX에 많이 사용한다.
		Map<String, Object> map = new HashMap<>();
		// Map에서 Value를 Object로 하게 되면 모든 데이터 타입의 값을 담을 수 있다.
		// java내부에서 처리할때는 Object로 처리를 하지 않는다. 내부에서 꺼낼때는 어떤 타입인지 모르기 때문에 어렵다
		/*
		 * 자바스크립트에서 AJAX로 던지면 나오는 DATA 형태 
		 * 다양한 종류의 데이터를 한꺼번에 넘기려면 JAVA에서는 Class가 많아야 한다.
		 * 그래서 Map를 통해서 여러 타입을 받을 수 있게 한다.
		 {
		 	"result" : true,
		 	"target" : {
		 							employeeId : 100,
		 							lastName : "King",
		 							...
		 						}
		 } 
		 */
		// 성공여부를 true로 돌려주고 싶다
		boolean isSuccessed = false;
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		return map; // return에는 널을 넣고 작업 하지마라. 놓치기 때문에 무조건 어떠한 값을 넣어 놓아야 한다. // 차라리 에러를 발생하게 null을 지워버려라
	}// end empUpdate

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();
		// 삭제가 안될 경우 : {}
		// 삭제가 될 경우 : { "employeeId" : 104}
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId);
		}
		return map;
	}// end empDelete

}// end class
