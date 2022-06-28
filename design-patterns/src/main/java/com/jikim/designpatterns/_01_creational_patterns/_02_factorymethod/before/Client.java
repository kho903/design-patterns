package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.before;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();

		Ship whiteship = ShipFactory.orderShip("WhiteShip", "keesun@mail.com");
		System.out.println(whiteship);

		Ship blackship = ShipFactory.orderShip("BlackShip", "keesun@mail.com");
		System.out.println(blackship);

	}
}
