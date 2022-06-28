package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v2_2;

public abstract class DefaultShipFactory implements ShipFactory {

	@Override
	public void sendEmailTo(String email, Ship ship) {
		System.out.println(ship.getName() + "를 완성하였습니다.");
	}
}
