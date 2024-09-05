package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	@Autowired // 필드 주입
	private EmpMapper empMapper;

	/*
	@Test
	void contextLoads() {
		assertNotNull(empMapper); 
	}// end contextLoads
	*/
	
	/*
	@Test
	void empList() {
		//전체조회 : 입력, X -> 출력, 1건 이상
		List<EmpVO> list = empMapper.selectEmpAllList(); // list는 결과가 없어도 널이 되지 않는다.
		assertTrue(!list.isEmpty()); // List타입은 값이 없어도 널이 안나오고 빈값이기 때문에 비어 있지 않는지 확인해서 처리하는게 맞다
	}
	*/
	
	/*
	@Test
	void empInfo() {
		//단건조회 : 입력, 사원번호(100) -> 출력, 사원이름(King)
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
		
	}
	*/
	/*
	@Test
	void empInsert() {
		// 사원등록 : 입력, 사원이름, 이메일, 업무
		//  				-> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hwang");
		empVO.setEmail("Hwang@gmail.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
		
	}
	*/
	/*
	@Test
	void empInsertFull() throws ParseException {
		// 사원등록 : 입력, 사원이름, 이메일, 입사일자, 업무, 급여
		//  				-> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hwang2");
		empVO.setEmail("Hwang2@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = sdf.parse("2024-08-15");
		empVO.setHireDate(today);
		empVO.setJobId("SA_REP");
		empVO.setSalary(15000.0);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
		
	}
	*/
	/*
	@Test
	void deleteEmpInfo() {
		int result = empMapper.deleteEmpInfo(4321);
		assertEquals(result, 1);
	}
	*/
	/*
	// Select 없이 Update
	@Test
	void empUpdateInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setEmail("Hwang3@gmail.com");
		
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		assertEquals(result, 1);
	}
	*/
	// Select 없이 Update
	@Test
	void empUpdateInfoDynamic() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		empVO.setLastName("Hwa");
		empVO.setJobId("ST_CLERK");
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		assertEquals(result, 1);
	}
}// end class
