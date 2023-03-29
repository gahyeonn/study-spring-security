package com.gahyeonn.ssiach9ex2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//현재 사용자를 사용하고 있지 않기 때문에 기본 UserDetailsService 구성 비활성화
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class SsiaCh9Ex2Application {

	public static void main(String[] args) {
		SpringApplication.run(SsiaCh9Ex2Application.class, args);
	}

}
