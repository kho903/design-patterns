package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v1;

public class GameServiceProxy extends GameService {

	@Override
	public void startGame() throws InterruptedException {
		long before = System.currentTimeMillis();
		super.startGame();
		System.out.println(System.currentTimeMillis() - before);
	}
}
