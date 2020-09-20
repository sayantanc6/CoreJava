package concurrency;


public class IncrementDecrement{
	Integer counter = 1;
	Thread  t1;
	Thread t2;
	  void incrmntdecrmnt() {
		Thread increment = new Thread(() -> {
			while (true) {
				synchronized (this) {
					while (this.counter == 1) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					this.counter++;
					System.out.println("increment : "+counter);
					this.notify();
				}
			}
		});
		
		Thread decrement = new Thread(() -> {
			while (true) {
				synchronized (this) {
					while (this.counter == 0) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					this.counter--;
					System.out.println("decrement : "+counter);
					this.notify();
				}
			}
		});
		increment.start();
		decrement.start();
		
	}

		
	}
