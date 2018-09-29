package com.ep1.creatingThreads;

class SleepThread extends Thread {

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

public class UsingThreadClass {
	public static void main(String[] args) {

		SleepThread thread = new SleepThread();
		thread.start();

		SleepThread thread1 = new SleepThread();
		thread1.start();

	}
}