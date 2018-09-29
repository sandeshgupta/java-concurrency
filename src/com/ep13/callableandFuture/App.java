package com.ep13.callableandFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newCachedThreadPool();

		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int value = random.nextInt(2000);

				if(value>1000)
					throw new IOException();
				
				Thread.sleep(value);

				return value;

			}
		});

		executor.shutdown();
		
		try {
			System.out.println(future.get());
		} catch (ExecutionException e) {
			//you can catch exception of the thread here. Cause of ExecutionException will give you actual exception
			e.printStackTrace();
		}
	}
}
