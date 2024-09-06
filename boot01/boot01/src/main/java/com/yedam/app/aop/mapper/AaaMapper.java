package com.yedam.app.aop.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AaaMapper {
	
	// 테이블 생성 : CREATE TABLE aaa (num NUMBER);
	@Insert("INSERT INTO aaa VALUES (#{values})")
	public int insert(String value);
	
}
