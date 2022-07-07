package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v1;


public class Client {

	public static void main(String[] args) throws InterruptedException {
		GameService gameService = new GameServiceProxy();
		gameService.startGame();
	}
}
