package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.after;

import java.time.LocalDateTime;

public class Restaurant {
	public void dinner(Integer id, LocalDateTime dateTime) {
		System.out.println("#" + id + " dinner in" + dateTime);
	}
}
