package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public String hello() {
		return "hello";
	}
}
