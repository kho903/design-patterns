package com.jikim.designpatterns._02_structural_patterns._12_proxy.java;

import java.lang.reflect.Proxy;

import com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2.DefaultGameService;
import com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2.GameService;

public class ProxyInJava {
	public static void main(String[] args) {
		ProxyInJava proxyInJava = new ProxyInJava();
		proxyInJava.dynamicProxy();
	}

	private void dynamicProxy() {
		GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
		gameServiceProxy.startGame();
	}

	private GameService getGameServiceProxy(GameService target) {
		return (GameService)Proxy.newProxyInstance(this.getClass().getClassLoader(),
			new Class[]{GameService.class}, (proxy, method, args) -> {
				System.out.println("Hello Dynamic Proxy 전");
				method.invoke(target, args);
				System.out.println("Hello Dynamic Proxy 후");
				return null;
			});
	}
}
