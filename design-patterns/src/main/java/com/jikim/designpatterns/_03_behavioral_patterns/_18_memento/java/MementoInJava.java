package com.jikim.designpatterns._03_behavioral_patterns._18_memento.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.jikim.designpatterns._03_behavioral_patterns._18_memento.after.Game;

public class MementoInJava {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Date
		Date date = new Date();
		System.out.println(date);
		long time = date.getTime();
		date.setTime(time);

		// Serializable
		Game game = new Game();
		game.setRedTeamScore(10);
		game.setBlueTeamScore(20);

		// 직렬화
		try (FileOutputStream fileOut = new FileOutputStream("GameSave.hex");
			 ObjectOutputStream out = new ObjectOutputStream(fileOut))
		{
			out.writeObject(game);
		}

		game.setBlueTeamScore(25);
		game.setRedTeamScore(15);

		// 역직렬화
		try (FileInputStream fileIn = new FileInputStream("GameSave.hex");
			 ObjectInputStream in = new ObjectInputStream(fileIn))
		{
			game = (Game) in.readObject();
			System.out.println(game.getBlueTeamScore());
			System.out.println(game.getRedTeamScore());
		}
	}
}
