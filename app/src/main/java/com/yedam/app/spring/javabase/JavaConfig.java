package com.yedam.app.spring.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class JavaConfig {
	@Bean
	public Chef chef() {
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		//return new Restaurant(chef); 
		Restaurant res = new Restaurant();
		res.setChef(chef);
		return res;
	}
}
