package com.jikim.designpatterns._03_behavioral_patterns._19_observer.java;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {

	private ApplicationEventPublisher publisher;

	public MyRunner(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		publisher.publishEvent(new MyEvent(/*this,*/ "hello spring event"));
	}
}
