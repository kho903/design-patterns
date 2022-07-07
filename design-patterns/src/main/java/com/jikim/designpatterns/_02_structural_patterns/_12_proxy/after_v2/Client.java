package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2;

public class Client {
	public static void main(String[] args) {
		GameService gameService = new GameServiceProxy();
		gameService.startGame();
	}
}
