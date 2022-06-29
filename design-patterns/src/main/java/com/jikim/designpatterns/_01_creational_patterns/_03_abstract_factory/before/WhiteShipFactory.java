package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.before;

public class WhiteShipFactory extends DefaultShipFactory {
	@Override
	public Ship createShip() {
		Ship ship = new WhiteShip();
		ship.setAnchor(new WhiteAnchor());
		ship.setWheel(new WhiteWheel());
		return ship;
	}
}
