package com.jikim.designpatterns._03_behavioral_patterns._14_command.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jikim.designpatterns._03_behavioral_patterns._14_command.after.Game;
import com.jikim.designpatterns._03_behavioral_patterns._14_command.after.Light;

public class CommandInJava {

	public static void main(String[] args) {
		Light light = new Light();
		Game game = new Game();
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		executorService.submit(light::on);
		executorService.submit(game::start);
		executorService.submit(game::end);
		executorService.submit(light::off);
		executorService.shutdown();
	}
}
