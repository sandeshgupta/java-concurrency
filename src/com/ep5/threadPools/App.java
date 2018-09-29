package com.ep5.threadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable {

	private int id;

	public Worker(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting " + id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending " + id);

	}
}

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 5; i++) {
			executor.submit(new Worker(i));
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All processed");
	}

}
