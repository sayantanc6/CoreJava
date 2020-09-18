package thread;


public class Multithread {
	Integer counter = 1;
	Thread  t1;
	Thread t2;
	  void dosomething() {
		Runnable increment = () -> {
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
		}; 
		
		Runnable decrement = () -> {
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
		};
		 t1 = new Thread(increment);
		 t2 = new Thread(decrement);
		t1.start();
		t2.start();
		
	}

		
	}
