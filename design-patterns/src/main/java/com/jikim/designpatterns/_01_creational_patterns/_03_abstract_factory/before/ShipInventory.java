package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.before;

public class ShipInventory {
	public static void main(String[] args) {
		ShipFactory shipFactory = new WhiteShipFactory();
		Ship ship = shipFactory.createShip();
		System.out.println(ship.getAnchor().getClass());
		System.out.println(ship.getWheel().getClass());
	}
}
