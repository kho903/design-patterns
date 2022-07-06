package com.jikim.designpatterns._02_structural_patterns._11_flyweight.java;

public class FlyweightInJava {
	public static void main(String[] args) {
		Integer i1 = Integer.valueOf(10);
		Integer i2 = Integer.valueOf(10);
		System.out.println(i1 == i2); // true : 캐싱된 값 (-128 to 127)

		Integer i3 = Integer.valueOf(128);
		Integer i4 = Integer.valueOf(128);
		System.out.println(i3 == i4); // false : 캐싱되지 않은 값
	}
}
