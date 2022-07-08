package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class LightOnCommand implements Command {

	private Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}
}
