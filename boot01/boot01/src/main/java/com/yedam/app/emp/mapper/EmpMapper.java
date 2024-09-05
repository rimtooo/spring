package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// 전체조회
	public List<EmpVO> selectEmpAllList(); // 전체건은 여러건 조회하기때문에 List로 받는다
	
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO); // 한건만 조회하기 때문에 조건이 필요하다
	
	// 등록, 수정, 삭제는 Int로 반환하기 때문에 int 처리한다.
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	
	// 수정
	public int updateEmpInfo(@Param("eid") int empId, @Param("emp")  EmpVO empVO);

	//삭제
	public int deleteEmpInfo(int empId);
}
