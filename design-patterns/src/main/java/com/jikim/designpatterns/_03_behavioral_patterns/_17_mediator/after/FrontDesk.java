package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.after;

import java.time.LocalDateTime;

public class FrontDesk {

	private CleaningService cleaningService = new CleaningService();

	private Restaurant restaurant = new Restaurant();

	public void getTowers(Guest guest, int numberOfTowers) {
		cleaningService.getTowers(guest.getId(), numberOfTowers);
	}

	public String getRoomNumberFor(Integer guestId) {
		return "#" + guestId;
	}

	public void dinner(Guest guest, LocalDateTime dateTime) {
		restaurant.dinner(guest.getId(), dateTime);
	}
}
