package com.jikim.designpatterns._03_behavioral_patterns._19_observer.java;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowInJava {
	public static void main(String[] args) {
		// 동기
		// Flow.Publisher<String> publisher = new Flow.Publisher<String>() {
		// 	@Override
		// 	public void subscribe(Flow.Subscriber<? super String> subscriber) {
		// 		subscriber.onNext("hello flow");
		// 		subscriber.onComplete();
		// 	}
		// };

		// 비동기
		Flow.Publisher<String> publisher = new SubmissionPublisher<>();

		Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {

			// 비동기
			private Flow.Subscription subscription;

			@Override
			public void onSubscribe(Flow.Subscription subscription) {
				// 비동기
				System.out.println("sub!");
				this.subscription = subscription;
				this.subscription.request(1);
			}

			@Override
			public void onNext(String item) {
				// 동기
				// System.out.println(item);
				// 비동기
				System.out.println("onNext called");
				System.out.println(Thread.currentThread().getName());
				System.out.println(item);
			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onComplete() {
				System.out.println("completed");
			}
		};
		publisher.subscribe(subscriber);
		((SubmissionPublisher)publisher).submit("hello java");
		// System.out.println("이것이 출력이 되기 전에 이미 처리가 다 끝남"); // 동기 기준
		System.out.println("이게 먼저 출력될 수도 있음");
	}
}
