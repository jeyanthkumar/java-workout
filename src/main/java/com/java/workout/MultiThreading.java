package com.java.workout;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.concurrent.FutureCallback;
import org.springframework.util.concurrent.ListenableFuture;

public class MultiThreading {

	public static void main(String[] args) {
		mythread myt = new mythread();
		myt.start();
		Thread t1 = new Thread(()->{
			System.out.println("Thread 1 started..");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread 1 ended..");
		});

		Thread t2 = new Thread(()->{
			System.out.println("Thread 2 started..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread 2 ended..");
		});

		//		t1.start();
		//		t2.start();

		try {
			ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

			Future future1 = executor.submit(t1);
			Future future2 = executor.submit(t2);

			while (!future1.isDone() || !future2.isDone()) {
//				System.out.println("future1.isDone(): "+future1.isDone() +" future2.isDone(): "+future2.isDone());
			}
			System.out.println("future1.isDone(): "+future1.isDone() +" future2.isDone(): "+future2.isDone());
			System.out.println("future1.get(): "+future1.get() +" future2.get(): "+future2.get());
			executor.shutdown();


			CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
				String str = "JK"; 
				System.out.println("Thread supplyAsyncFuture started..");
				try {
					Thread.sleep(2000);
					int a= 9/0;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread supplyAsyncFuture ended..");
				return str;
			}); 
			ExecutorService executor1 = Executors.newFixedThreadPool(5);
			CompletableFuture runAsyncFuture = CompletableFuture.runAsync(() -> {
				String str = "JK"; 
				System.out.println("Thread runAsyncFuture started..");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread runAsyncFuture ended..");
			}, executor1);

			supplyAsyncFuture.whenComplete((res,ex)->{
				if(ex==null) {
					System.out.println("Success Response: "+res);
				}else {
					System.out.println("Failure Response: "+res+" Exception: "+ex);
				}
			});

			System.out.println("supplyAsyncFuture: "+supplyAsyncFuture.getNow("absent"));
			System.out.println("supplyAsyncFuture: "+supplyAsyncFuture.completedStage("completed....").toCompletableFuture().get());
			System.out.println("supplyAsyncFuture: "+supplyAsyncFuture.get());
			System.out.println("runAsyncFuture: "+runAsyncFuture.completedStage("completed....").toCompletableFuture().get());
			System.out.println("runAsyncFuture: "+runAsyncFuture.get());



		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}

class mythread extends Thread{
	public void run() {
		System.out.println("Run method..");
	}
//	public void start() {
//		System.out.println("Start method.."); // Start method get called when thread.start();
//	}
}
