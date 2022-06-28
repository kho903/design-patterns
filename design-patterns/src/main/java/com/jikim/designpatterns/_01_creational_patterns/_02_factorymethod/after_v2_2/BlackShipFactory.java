package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v2_2;

public class BlackShipFactory extends DefaultShipFactory {
	@Override
	public Ship createShip() {
		return new BlackShip();
	}
}
