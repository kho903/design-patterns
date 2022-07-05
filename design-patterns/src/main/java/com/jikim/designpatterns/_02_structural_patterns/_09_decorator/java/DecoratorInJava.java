package com.jikim.designpatterns._02_structural_patterns._09_decorator.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

public class DecoratorInJava {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(new Book());

		List books = Collections.checkedList(list, Book.class);
		list.add(new Item());
		// books.add(new Item()); // ClassCastException

		List unmodifiableCollection = Collections.unmodifiableList(list);
		list.add(new Item());
		// unmodifiableCollection.add(new Book()); // UnsupportedOperationException

		// 서블릿 요청 또는 응답 랩퍼
		HttpServletRequestWrapper requestWrapper;
		HttpServletResponseWrapper responseWrapper;
	}

	private static class Book {
	}

	private static class Item {
	}
}
