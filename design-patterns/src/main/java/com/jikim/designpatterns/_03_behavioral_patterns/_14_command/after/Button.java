package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class Button {

	private Command command;

	public Button(Command command) {
		this.command = command;
	}

	public void press() {
		command.execute();
	}

	public static void main(String[] args) {
		Button button = new Button(new LightOnCommand(new Light()));
		button.press();
	}
}
