package com.ep7.producerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

	public static void main(String[] args) {
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		producer.start();
		consumer.start();

	}

	private static void producer() throws InterruptedException {
		Random random = new Random();

		while (true) {
			Thread.sleep(100);
			queue.put(random.nextInt(100));
			System.out.println("After add, queue size = " + queue.size());
		}
	}
	
	private static void consumer() throws InterruptedException {
//		/Random random = new Random();
		Integer value;

		while (true) {
			Thread.sleep(1000);
			value = queue.take();
			System.out.println("value taken = " + value);
			System.out.println("After remove, queue size = " + queue.size());
		}
	}
}
