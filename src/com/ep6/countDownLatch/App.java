package com.ep6.countDownLatch;

import java.util.concurrent.CountDownLatch;

class Waiter implements Runnable {

	private CountDownLatch latch;

	Waiter(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("start wait");
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("end wait");
	}
}

class Decrementer implements Runnable {

	private CountDownLatch latch;

	public Decrementer(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("initial latch count  "+latch.getCount());

		try {
			Thread.sleep(2000);
			latch.countDown();
			System.out.println(latch.getCount());

			Thread.sleep(2000);
			latch.countDown();
			System.out.println(latch.getCount());

			Thread.sleep(2000);
			latch.countDown();
			System.out.println(latch.getCount());

			Thread.sleep(2000);
			latch.countDown();
			System.out.println(latch.getCount());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

public class App {
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		Thread wait = new Thread(new Waiter(latch));
		Thread decrement = new Thread(new Decrementer(latch));
		
		wait.start();
		decrement.start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed");
		
	}
}
