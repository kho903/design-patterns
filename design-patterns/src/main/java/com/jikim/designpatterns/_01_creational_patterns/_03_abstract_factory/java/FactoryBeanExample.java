package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after.Ship;

public class FactoryBeanExample {

	public static void main(String[] args) {
		// xml 설정
		/*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
		Ship whiteShip = applicationContext.getBean("whiteShip", Ship.class);
		System.out.println(whiteShip.getName());*/

		// Java 설정
		// 2가지 방법
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
		// ShipFactory bean = applicationContext.getBean(ShipFactory.class);
		Ship bean = applicationContext.getBean(Ship.class);
		System.out.println(bean);

	}
}
