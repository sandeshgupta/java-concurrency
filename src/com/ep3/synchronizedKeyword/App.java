package com.ep3.synchronizedKeyword;

public class App {

	private static int count;

	public synchronized void incrementCount() {
		count++;
	}

	public static void main(String[] args) {
		App app = new App();
		app.doWork();

	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20000; i++) {
					incrementCount();
				}
			}
		});
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20000; i++) {
					incrementCount();
				}
			}
		});
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(count);
	}

}
