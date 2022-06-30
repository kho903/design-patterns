package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after;

public abstract class DefaultShipFactory implements ShipFactory {
	@Override
	public void sendEmailTo(String email, Ship ship) {
		System.out.println(ship.getName() + "이 완성되었습니다.");
	}
}
