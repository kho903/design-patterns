package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2;

public class GameServiceProxy implements GameService {

	private GameService gameService;

	@Override
	public void startGame() {
		long before = System.currentTimeMillis();
		if (gameService == null) {
			this.gameService = new DefaultGameService();
		}
		gameService.startGame();
		System.out.println(System.currentTimeMillis() - before);
	}
}
