package com.ep4.syncCodeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	Object lock1 = new Object();
	Object lock2 = new Object();

	public static void main(String[] args) {
		App app = new App();
		app.doWork();

	}

	private void updateList1() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (lock1) {
			list1.add(random.nextInt(100));
		}
		
		
	}

	private  void updateList2() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (lock2) {
			list2.add(random.nextInt(100));
		}
	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					updateList1();
					updateList2();

				}

			}
		});
		

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					updateList1();
					updateList2();

				}
			}
		});
		
		long start = System.currentTimeMillis();
		
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		System.out.println(list1.size());
		System.out.println(list2.size());

	}

}
