package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

import com.jikim.designpatterns._02_structural_patterns._07_bridge.before.Champion;

public class App {
	public static void main(String[] args) {
		Champion kda아리 = new 아리(new KDA());
		kda아리.skillQ();
		kda아리.skillW();
		kda아리.skillE();
		kda아리.skillR();

		Champion poolParty아리 = new 아리(new PoolParty());
		poolParty아리.skillQ();
		poolParty아리.skillW();
		poolParty아리.skillE();
		poolParty아리.skillR();
	}
}
