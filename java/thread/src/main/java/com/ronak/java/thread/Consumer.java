package com.ronak.java.thread;

import java.util.List;

public class Consumer extends Thread {
	private List<Integer> messages;
	private int size;

	public Consumer(List<Integer> messages, int size) {
		this.messages = messages;
		this.size = size;
	}

	public void run() {
		while (true) {
			try {
				while (messages.size() == 0) {
					synchronized (messages) {
						System.out.println("Queue is empty. Consumer" + Thread.currentThread().getName() + " is waiting , size: "
								+ messages.size());
						messages.wait();
					}

				}

				synchronized (messages) {
					Integer i = messages.remove(0);
					System.out.println("Consumer Removing " + i);
					Thread.sleep(1500);
					messages.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

	}

}
