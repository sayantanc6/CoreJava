package concurrency;

public class EntryPoint {

	public static void main(String[] args) {
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
