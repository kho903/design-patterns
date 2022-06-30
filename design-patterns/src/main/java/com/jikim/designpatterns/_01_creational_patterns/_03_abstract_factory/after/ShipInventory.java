package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after;

public class ShipInventory {
	public static void main(String[] args) {

		ShipFactory shipFactory = new WhiteShipFactory(new WhiteShipPartsFactory());
		Ship ship = shipFactory.createShip();
		System.out.println(ship.getAnchor().getClass());
		System.out.println(ship.getWheel().getClass());
		System.out.println();

		ShipFactory shipProFactory = new WhiteShipFactory(new WhitePartsProFactory());
		Ship shipPro = shipProFactory.createShip();
		System.out.println(shipPro.getAnchor().getClass());
		System.out.println(shipPro.getWheel().getClass());


	}
}
