package com.jikim.designpatterns._01_creational_patterns._01_singleton.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	@Bean
	public String hello() {
		return "hello";
	}
}
