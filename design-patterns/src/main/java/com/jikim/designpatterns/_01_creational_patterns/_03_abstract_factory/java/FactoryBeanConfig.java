package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {

	@Bean
	public ShipFactory shipFactory() {
		return new ShipFactory();
	}
}
