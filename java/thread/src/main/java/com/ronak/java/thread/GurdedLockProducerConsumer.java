package com.ronak.java.thread;

import java.util.ArrayList;
import java.util.List;

public class GurdedLockProducerConsumer {

	public static void main(String[] args) {

		List<Integer> messages = new ArrayList<Integer>(0);

		Thread producer = new Thread(new Producer(messages, 4));
		Thread consumer = new Thread(new Consumer(messages, 4));
		producer.start();
		consumer.start();

	}

}
