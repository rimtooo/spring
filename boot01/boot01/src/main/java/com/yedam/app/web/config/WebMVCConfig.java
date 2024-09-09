package com.yedam.app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Value("${file.upload.path}")
	String uploadPath;
	
	
	// 리소스 핸들링
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println(uploadPath);
		registry
		.addResourceHandler("/imgs/**") // URL
		.addResourceLocations("file:///" + uploadPath); // 실제경로
	}
	
}
