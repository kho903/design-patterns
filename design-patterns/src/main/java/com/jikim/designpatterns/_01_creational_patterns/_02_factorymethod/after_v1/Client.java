package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v1;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();

		Ship whiteShip = new WhiteShipFactory().orderShip("WhiteShip", "keesun@mail.com");
		System.out.println(whiteShip);

		Ship blackShip = new BlackShipFactory().orderShip("BlackShip", "keesun@mail.com");
		System.out.println(blackShip);

	}
}
