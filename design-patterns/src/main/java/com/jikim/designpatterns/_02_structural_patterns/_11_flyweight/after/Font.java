package com.jikim.designpatterns._02_structural_patterns._11_flyweight.after;

public final class Font {

	final String family;

	final int size;

	public Font(String family, int size) {
		this.family = family;
		this.size = size;
	}

	public String getFamily() {
		return family;
	}

	public int getSize() {
		return size;
	}
}
