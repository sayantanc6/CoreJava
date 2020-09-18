package thread;

public class Mymain {
	/* Source of information :- 
	 * https://stackoverflow.com/questions/13249835/java-does-wait-release-lock-from-synchronized-block#:~:text=When%20thread%20calls%20wait%20it,than%20goes%20to%20WAITING%20state.&text=So%20to%20summarize%2C%20thread%20acquires,synchronized%20method%20after%20the%20wait.
	 * https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait()
	 * https://howtodoinjava.com/java/multi-threading/multithreading-difference-between-lock-and-monitor/
	 */
	

	public static void main(String[] args) {
		Multithread multi = new Multithread();
		multi.dosomething();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 System.exit(0);
	}

}
