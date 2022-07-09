package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.jikim.designpatterns._03_behavioral_patterns._16_iterator.after.Board;
import com.jikim.designpatterns._03_behavioral_patterns._16_iterator.after.Post;

public class IteratorInJava {
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		Enumeration enumeration;
		Iterator iterator;

		Board board = new Board();
		board.addPost("디자인 패턴 게임");
		board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
		board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

		// Iterator<Post> iterator1 = board.getPosts().iterator();
		// while (iterator1.hasNext()) {
		// 	Post next = iterator1.next();
		// 	System.out.println(next.getTitle());
		// }
		board.getPosts().iterator().forEachRemaining(p -> System.out.println(p.getTitle()));

		// StAX = Streaming API for XML : 콘솔 기반의 API, 이터레이터 기반의 API
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("Book.xml"));

		while (reader.hasNext()) {
			XMLEvent nextEvent = reader.nextEvent();
			// System.out.println(nextEvent);
			if (nextEvent.isStartElement()) {
				StartElement startElement = nextEvent.asStartElement();
				QName name = startElement.getName();
				if (name.getLocalPart().equals("book")) {
					// System.out.println(nextEvent);
					Attribute title = startElement.getAttributeByName(new QName("title"));
					System.out.println(title.getValue());
				}
			}
		}
	}
}
