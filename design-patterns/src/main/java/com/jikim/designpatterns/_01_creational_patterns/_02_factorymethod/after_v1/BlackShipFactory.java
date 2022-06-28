package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v1;

public class BlackShipFactory implements ShipFactory {
	@Override
	public Ship createShip() {
		return new BlackShip();
	}
}
