package com.ronak.java.thread;

/**
 * The join method allows one thread to wait for the completion of another. If t
 * is a Thread object whose thread is currently executing. t.join(); causes the
 * current thread to pause execution until t's thread terminates.
 * 
 * Like sleep, join responds to an interrupt by exiting with an
 * InterruptedException.
 * 
 * Running following program with commented join will cause threads to run in
 * random manner. call by uncommenting join will make in run in order.
 * 
 * @author Ronak
 *
 */
public class Join implements Runnable {

	public static void main(String[] args) {
		Join join = new Join();
		Thread t1 = new Thread(join, "t1");
		Thread t2 = new Thread(join, "t2");
		Thread t3 = new Thread(join, "t3");
		try {
			t1.start();
			t1.join();// wait for t1 to finish
			t2.start();
			t2.join();// wait for t2 to finish
			t3.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			System.out.println("Run executing from " + Thread.currentThread().getName());
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
