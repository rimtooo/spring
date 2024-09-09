package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // 메모리상 인증정보 등록 => 테스트 전용
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails user
				= User.builder()
				.username("user1")
				.password(passwordEncoder().encode("1234")) // 암호화 상태로 넣기
				.roles("USER") // ROLE_USER 권한 : 무조건 앞에 ROLE_붙음
				//.authorities("ROLE_USER") // 권한 : 자유롭게
				.build();
		
		
		UserDetails admin
		= User.builder()
		.username("admin1")
		.password(passwordEncoder().encode("1234")) // 암호화 상태로 넣기
		//.roles("ADMIN") // ROLE_USER 권한 : 무조건 앞에 ROLE_붙음
		.authorities("ROLE_ADMIN") // 권한 : 자유롭게
		.build();
		
		return new InMemoryUserDetailsManager(user, admin);
		
	}
	
	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// 적용될 Security 설정
		// => URI에 적용될 권한
		http
			.authorizeHttpRequests( authrize -> authrize
					.requestMatchers("/", "/all").permitAll()
					.requestMatchers("/user/**").hasRole("USER")
					.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
					.anyRequest().authenticated()
			)
			.formLogin(formLogin -> formLogin
					  .defaultSuccessUrl("/all"))
			.logout(logout -> logout
					.logoutSuccessUrl("/all"));
		
		return http.build();
	}
}
