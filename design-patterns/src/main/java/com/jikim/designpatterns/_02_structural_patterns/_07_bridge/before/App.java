package com.jikim.designpatterns._02_structural_patterns._07_bridge.before;

public class App {
	public static void main(String[] args) {
		Champion kda아리 = new KDA아리();
		kda아리.skillQ();
		kda아리.skillW();
		kda아리.skillE();
		kda아리.skillR();
	}
}
