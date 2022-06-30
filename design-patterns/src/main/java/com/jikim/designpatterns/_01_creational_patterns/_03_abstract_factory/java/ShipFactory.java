package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.java;

import org.springframework.beans.factory.FactoryBean;

import com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after.Ship;
import com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after.WhiteShip;

public class ShipFactory implements FactoryBean<Ship> {
	@Override
	public Ship getObject() throws Exception {
		Ship ship = new WhiteShip();
		ship.setName("whiteShip");
		return ship;
	}

	@Override
	public Class<?> getObjectType() {
		return Ship.class;
	}
}
