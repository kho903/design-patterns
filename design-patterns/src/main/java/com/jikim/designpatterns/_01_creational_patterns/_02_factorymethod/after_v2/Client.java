package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v2;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		client.print(new WhiteShipFactory(), "WhiteShip", "keesun@mail.com");
		client.print(new BlackShipFactory(), "blackShip", "keesun@mail.com");
	}

	private void print(ShipFactory shipFactory, String name, String email) {
		System.out.println(shipFactory.orderShip(name, email));
	}

}
