package com.ep8.producerConsumerWaitNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class App {

	static Processor processor = new Processor();

	public static void main(String[] args) {
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				processor.produce();
			}
		});

		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				processor.consume();
			}
		});

		producer.start();
		consumer.start();
	}
}

class Processor {

	Queue<Integer> queue = new LinkedList<Integer>();

	public void produce() {
		Random random = new Random();
		int value;

		while (true) {
			synchronized (this) {

				if (queue.size() < 10) {
					value = random.nextInt(100);
					System.out.println("Produced = " + value);
					queue.add(value);
					notify();
				} else {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void consume() {
		// Let producer populate the queue
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			synchronized (this) {
				if (queue.size() == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Consumed = " + queue.peek());
					queue.remove();
					notify();
				}
			}
		}
	}

}