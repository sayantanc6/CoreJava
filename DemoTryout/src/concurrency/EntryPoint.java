package concurrency;

import java.util.concurrent.ExecutionException;

public class EntryPoint {
	
	/*
	 * source of information :-
	 * https://www.callicoder.com/java-8-completablefuture-tutorial/
	 * https://www.baeldung.com/java-completablefuture
	 * */

	public static void main(String[] args) throws InterruptedException,ExecutionException{
		
		CF cf = new CF();
		cf.asynchronousComputation();
		cf.supplyasynchronous();
		cf.thenApplyChain();
		cf.supplyasyncthenaccept();
		cf.thencompose();
		cf.thencombine();
		cf.allFutures();
		cf.anyFuture();
		SharedResourceLockScheduling srls =new SharedResourceLockScheduling();
		srls.pushpop();
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0); 
	}
}
