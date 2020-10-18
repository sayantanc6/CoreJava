package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CF {
	
	CompletableFuture<Void> cf;
	CompletableFuture<String> cf_1;

	public void asynchronousComputation() throws InterruptedException,ExecutionException{
		 cf = CompletableFuture.runAsync(new Runnable() {
			
			@Override
			public void run() { 
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
		            throw new IllegalStateException(e);
				}
		        System.out.println("I'll run in a separate thread than the main thread.");
			}
		},Executors.newFixedThreadPool(10)); 
		 Void result = cf.get();
		 System.out.println(result); 
	}
	
	public void supplyasynchronous() throws InterruptedException,ExecutionException{
		 cf_1 = CompletableFuture.supplyAsync(() -> {
			 try {
			        TimeUnit.SECONDS.sleep(1);
			    } catch (InterruptedException e) {
			        throw new IllegalStateException(e);
			    }
			    return "Result of the asynchronous computation";
		 },Executors.newFixedThreadPool(10)); 
		 String result = cf_1.get();
		 System.out.println(result); 
	}
	
	public void thenApplyChain() throws InterruptedException,ExecutionException{
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			   try {
			       TimeUnit.SECONDS.sleep(1);
			   } catch (InterruptedException e) {
			       throw new IllegalStateException(e);
			   }
			   return "Rajeev";
			}).thenApply(name -> {
				   return "Hello " + name;
			});
		System.out.println(whatsYourNameFuture.get()); 
	}
	
	public void supplyasyncthenaccept() throws InterruptedException,ExecutionException{
		cf_1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Result of the asynchronous computation";
			},Executors.newFixedThreadPool(10));
		CompletableFuture<Void> future = cf_1.thenAccept(s -> System.out.println("Computation returned: " + s));
				 
				Void result = future.get();
				System.out.println(result); 
		}

	public void thencompose() throws InterruptedException,ExecutionException{
		CompletableFuture<String> completableFuture  = CompletableFuture.supplyAsync(() -> "Hello")
		    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
		System.out.println(completableFuture.get()); 
	}
	
	public void thencombine() throws InterruptedException,ExecutionException{
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
		    .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
		System.out.println(completableFuture.get()); 
	}
	
	public void allFutures() throws InterruptedException,ExecutionException{
		CompletableFuture<String> future1  = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2  = CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3   = CompletableFuture.supplyAsync(() -> "World");
		 
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
		System.out.println(combinedFuture.get()); 
	} 
	
	public void anyFuture() throws InterruptedException,ExecutionException{
		CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return "Result of Future 1";
		}), 
				CompletableFuture.supplyAsync(() -> {
				    try {
				        TimeUnit.SECONDS.sleep(1);
				    } catch (InterruptedException e) {
				       throw new IllegalStateException(e);
				    }
				    return "Result of Future 2";
				}),
				CompletableFuture.supplyAsync(() -> {
				    try {
				        TimeUnit.SECONDS.sleep(3);
				    } catch (InterruptedException e) {
				       throw new IllegalStateException(e);
				    }
				    return "Result of Future 3";
				}));
		System.out.println(anyOfFuture.get());
	}
}
