package com.ep2.BasicThreadSynchronisation;

import java.util.Scanner;

class Processor extends Thread {
	private volatile boolean running = true;

	@Override
	public void run() {
		while (running) {
			System.out.println("Thread running");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void shutdown() {
		running = false;
	}

}

public class VolatileKeyword {
	public static void main(String[] args) {
		Processor processor = new Processor();
		processor.start();

		Scanner sc = new Scanner(System.in);
		sc.nextLine();

		processor.shutdown();

		sc.close();
	}
}
