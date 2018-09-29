package com.ep14.interruptingThread;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			Random rand = new Random();

			@Override
			public void run() {
				for (int i = 0; i < 1E8; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("interrupted");
						break;
					}
					Math.sin(rand.nextDouble());
				}

			}
		});

		t1.start();

		t1.interrupt();

		t1.join();

	}
}
