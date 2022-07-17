package com.jikim.designpatterns._03_behavioral_patterns._19_observer.java;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener /*implements ApplicationListener<MyEvent>*/ {
	// @Override
	@EventListener(MyEvent.class)
	public void onApplicationEvent(MyEvent event) {
		// System.out.println(event.getSource());
		System.out.println(event.getMessage());
	}
}
