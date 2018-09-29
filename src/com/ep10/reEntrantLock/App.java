package com.ep10.reEntrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
	public static void main(String[] args) throws InterruptedException {
		Processor process = new Processor();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					process.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					process.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		process.print();
	}
}

class Processor {
	private int count = 0;
	private static Lock lock = new ReentrantLock();
	private static Condition cond = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		lock.lock();

		// wait till second thread takes input
		cond.await();
		System.out.println("Waited");

		try {
			increment();
		} finally {
			lock.unlock();
		}

	}

	public void secondThread() throws InterruptedException {

		Thread.sleep(2000);

		lock.lock();
		cond.signal();
		System.out.println("signalled");

		try {
			increment();
		} finally {
			lock.unlock();
		}

	}

	public void print() {
		System.out.println("count=" + count);
	}
}