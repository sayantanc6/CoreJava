package concurrency;

import java.util.ArrayList;
import java.util.List;


public class PushPop {

	List<String> list;
	int inturn = 0;
	
	public void pushpop() {
		list = new ArrayList<>(); 
		
		Thread pushA = new Thread(() ->  {
			while (true) {
				synchronized (this) {
					while (this.inturn != 0 && this.inturn % 3 != 0) { // 0,3,6,9,...
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					list.add("A");
					this.inturn = this.inturn + 1;
					list.forEach(element -> {
						System.out.print(element+" "); 
					});
					System.out.println();
					this.notifyAll();
				}
			}
		},"pushA");
		
		Thread pushB = new Thread(() ->  {
			while (true) {
				synchronized (this) {
					while (this.inturn != 1 && this.inturn % 3 != 1) { // 1,4,7,...

						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					list.add("B");
					this.inturn = this.inturn + 1;
					list.forEach(element -> {
						System.out.print(element+" ");
					});
					System.out.println();
					this.notifyAll();
				}
			}
		},"pushB");
		
		Thread pushC = new Thread(() ->  {
			while (true) {
				synchronized (this) {
					while (this.inturn != 2 && this.inturn % 3 != 2) { // 2,5,8,...

						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					list.add("C");
					this.inturn = this.inturn + 1;
					list.forEach(element -> {
						System.out.print(element+" ");
					});
					System.out.println();
					this.notifyAll();
				}
			}
		},"pushC");
		
		
		  Thread popA = new Thread(() -> { 
			  while (true) { 
				  synchronized (this) { 
					  while(this.inturn != 0 && (list.lastIndexOf("A") != (list.size() -1))) { //  0,3,6,9,... 
						  
		  			try { 
		  					this.wait(); 
		  				} catch (InterruptedException e) {
		  						e.printStackTrace(); 
		  				} 
					 } 
					  list.remove(list.lastIndexOf("A")); 
					  this.inturn = this.inturn + 1; 
					  list.forEach(element -> { 
						  System.out.print(element+" "); 
					});
					  System.out.println(); 
					  this.notifyAll(); 
				} 
			} 
		},"popA");
		  
		  Thread popB = new Thread(() -> { 
			  while (true) { 
				  synchronized (this) { 
					  while(this.inturn != 0 && (list.lastIndexOf("B") != (list.size() -1))) { //  0,3,6,9,... 
						  
		  			try { 
		  					this.wait(); 
		  				} catch (InterruptedException e) {
		  						e.printStackTrace(); 
		  				} 
					 } 
					  list.remove(list.lastIndexOf("A")); 
					  this.inturn = this.inturn + 1; 
					  list.forEach(element -> { 
						  System.out.print(element+" "); 
					});
					  System.out.println(); 
					  this.notifyAll(); 
				} 
			} 
		},"popB");
		  
		  Thread popC = new Thread(() -> { 
			  while (true) { 
				  synchronized (this) { 
					  while(this.inturn != 0 && (list.lastIndexOf("C") != (list.size() -1))) { //  0,3,6,9,... 
						  
		  			try { 
		  					this.wait(); 
		  				} catch (InterruptedException e) {
		  						e.printStackTrace(); 
		  				} 
					 } 
					  list.remove(list.lastIndexOf("A")); 
					  this.inturn = this.inturn + 1; 
					  list.forEach(element -> { 
						  System.out.print(element+" "); 
					});
					  System.out.println(); 
					  this.notifyAll(); 
				} 
			} 
		},"popC");
		
		 
		pushA.start();
		pushB.start();
		pushC.start();
		popA.start(); 
		popB.start();
		popC.start();
		 
		
	}
	
	
}
