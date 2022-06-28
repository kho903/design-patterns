package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v2;

public class WhiteShipFactory implements ShipFactory {
	@Override
	public Ship createShip() {
		return new WhiteShip();
	}
}
