package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after;

public class WhiteShipPartsFactory implements ShipPartsFactory {
	@Override
	public Anchor createAnchor() {
		return new WhiteAnchor();
	}

	@Override
	public Wheel createWheel() {
		return new WhiteWheel();
	}
}
