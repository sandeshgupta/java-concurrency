package com.ep12.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) throws InterruptedException {

		Connection conn = new Connection();
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 200; i++) {
			executor.submit(new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						conn.connect();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}));

		}
		executor.shutdown();

		executor.awaitTermination(1, TimeUnit.DAYS);

		System.out.println(conn.getNoofConnections());
	}
}

class Connection {
	private int count = 0;
	Semaphore semaphore = new Semaphore(10);

	public void connect() throws InterruptedException {
		semaphore.acquire();
		
		synchronized (this) {
			count++;
			System.out.println("count inc =" + count);
		}

		Thread.sleep(2000);

		synchronized (this) {
			count--;
			System.out.println("count dec=" + count);

		}
		semaphore.release();

	}

	public int getNoofConnections() {
		return count;
	}
}