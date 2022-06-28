package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v1;

public interface ShipFactory {

	default Ship orderShip(String name, String email) {
		validate(name, email);
		prepareFor(name);
		Ship ship = createShip();
		sendEmailTo(email, ship);
		return ship;
	}

	Ship createShip();

	private static void sendEmailTo(String email, Ship ship) {
		System.out.println(ship.getName() + "를 완성하였습니다.");
	}

	private void validate(String name, String email) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("배 이름을 지어주세요.");
		}

		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("이메일을 남겨주세요.");
		}
	}

	private static void prepareFor(String name) {
		System.out.println(name + " 만들 준비 중");
	}

}
