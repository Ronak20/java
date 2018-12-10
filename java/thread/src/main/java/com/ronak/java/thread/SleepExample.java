package com.ronak.java.thread;

public class SleepExample implements Runnable{

	public void run() {
		try {
			System.out.println("Hello from a thread! 1");
			Thread.sleep(1000);//with millisecond
			System.out.println("Hello from a thread! 2");
			Thread.sleep(1000,0);//with millisecond + nano second
			System.out.println("Hello from a thread! 3");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException I am here because some thread interrupted me");
		}
	}

	public static void main(String[] args) {
		(new Thread(new SleepExample())).start();
	}

}
