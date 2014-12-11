package com.pikia.z.test;

/**
 * @author Rollen-Holt 继承Thread类,直接调用run方法
 * */
public class ThreadTest extends Thread {
	public ThreadTest() {

	}

	public ThreadTest(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "运行     " + i);
		}
	}

	private String name;

	public static void main(String[] args) {
		ThreadTest h1 = new ThreadTest("A");
		ThreadTest h2 = new ThreadTest("B");
//		h1.run();
//		h2.run();
		h1.start();
		h2.start();
	}
}