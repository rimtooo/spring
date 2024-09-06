package com.yedam.app.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;
import com.yedam.app.aop.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {
	private AaaMapper aaaMapper;
	
	@Autowired
	AaaServiceImpl(AaaMapper aaaMapper) {
		this.aaaMapper = aaaMapper;
	}
	
	@Transactional() // 오토커밋 일시적으로 풀어줌.
	@Override		 
	public void insert() {
		aaaMapper.insert("101");
		aaaMapper.insert("a101");
	}
}
