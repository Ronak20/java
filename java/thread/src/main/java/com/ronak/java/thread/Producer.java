package com.ronak.java.thread;

import java.util.List;

public class Producer extends Thread {
	private List<Integer> messages;
	private int size;

	public Producer(List<Integer> messages, int size) {
		this.messages = messages;
		this.size = size;
	}

	public void run() {
		for (int i = 0; i < 7; i++) {
			try {
				while (messages.size() == size) {
					synchronized (messages) {
						System.out.println("Queue is full. Producer " + Thread.currentThread().getName() + " is waiting , size: "
								+ messages.size());

						messages.wait();
					}
				}

				synchronized (messages) {
					System.out.println("Producer Adding " + i);
					messages.add(i);
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
