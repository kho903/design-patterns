package com.jikim.designpatterns._02_structural_patterns._07_bridge.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jExample {

	private static Logger logger = LoggerFactory.getLogger(Slf4jExample.class);

	public static void main(String[] args) {
		logger.info("hello logger");
	}
}
