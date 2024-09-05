package com.yedam.app.spring.anotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	private Chef chef;
	
	//생성자 인젝션
	@Autowired
	Restaurant(Chef chef){
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}
	
	//세터 인젝션 -> 반드시 기본 생성자 있어야 함.
	//Restaurant() {} //기본 생성자
	Restaurant() {
		System.out.println("기본 생성자");
	}
	
	@Autowired
	public void setChef(Chef chef) {
		this.chef = chef;
		System.out.println("세터 인젝션");
	}
	
	public void run() {
		chef.cooking();
	}
}
