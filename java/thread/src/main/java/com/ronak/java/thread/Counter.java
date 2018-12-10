package com.ronak.java.thread;

/**
 * Suppose Thread A invokes increment at about the same time Thread B invokes
 * decrement. If the initial value of c is 0, their interleaved actions might
 * follow this sequence:
 * 
 * Thread A: Retrieve c. 
 * Thread B: Retrieve c. 
 * Thread A: Increment retrieved value; result is 1. 
 * Thread B: Decrement retrieved value; result is -1. 
 * Thread A: Store result in c; c is now 1. 
 * Thread B: Store result in c; c is now -1.
 * 
 * Thread A's result is lost, overwritten by Thread B.
 * 
 * @author Ronak
 *
 */
public class Counter {

	private int c = 0;

	public void increment() {
		c++;
	}

	public void decrement() {
		c--;
	}

	public int value() {
		return c;
	}

	public static void main(String[] args) {

	}

}
