package com.ronak.java.thread;

/**
 * If thread does not call method which throws InterruptException The check
 * using Thread.interrupted() for interrupts.
 * 
 * @author Ronak
 *
 */
public class HandleInterrupt implements Runnable {

	public void run() {
		for (int i = 0; i < 6; i++) {
			System.out.println("Hello from a thread! " + i);
			if (Thread.interrupted()) {
				System.out.println("We've been interrupted. Let's return.");
				return;
			}
		}
	}

	public static void main(String[] args) {
		(new Thread(new HandleInterrupt())).start();
	}

}
