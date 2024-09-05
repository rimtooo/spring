package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper") // Mybatis mapper 스캔, ** 은 기능을 의미함. 여기에 존재하는 모든 것을 스캔한다.
public class Boot01Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot01Application.class, args);
	}

}
