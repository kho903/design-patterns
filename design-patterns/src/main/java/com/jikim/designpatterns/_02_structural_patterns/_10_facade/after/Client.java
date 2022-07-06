package com.jikim.designpatterns._02_structural_patterns._10_facade.after;

public class Client {
	public static void main(String[] args) {
		EmailSettings emailSettings = new EmailSettings();
		emailSettings.setHost("127.0.0.1");
		EmailSender emailSender = new EmailSender(emailSettings);
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setFrom("gmldnr2222@naver.com");
		emailMessage.setTo("keesun@whiteship.me");
		emailMessage.setSubject("Test Mail from Java Program");
		emailMessage.setText("Facade Pattern");
		emailSender.sendEmail(emailMessage);
	}
}
