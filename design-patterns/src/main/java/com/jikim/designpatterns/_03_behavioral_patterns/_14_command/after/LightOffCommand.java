package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class LightOffCommand implements Command {

	private Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		new LightOnCommand(this.light).execute();
	}
}
