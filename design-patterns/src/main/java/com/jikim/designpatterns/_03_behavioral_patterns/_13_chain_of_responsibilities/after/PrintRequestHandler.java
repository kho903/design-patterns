package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public class PrintRequestHandler extends RequestHandler {

	public PrintRequestHandler(RequestHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void handle(Request request) {
		System.out.println(request.getBody());
		super.handle(request);
	}
}
