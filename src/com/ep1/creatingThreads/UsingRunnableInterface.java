package com.ep1.creatingThreads;

class SleepThreadRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class UsingRunnableInterface {
	public static void main(String[] args) {

		Thread thread = new Thread(new SleepThreadRunnable());
		thread.start();

		Thread thread1 = new Thread(new SleepThreadRunnable());
		thread1.start();

	}
}