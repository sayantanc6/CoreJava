package concurrency;

import java.util.ArrayList;
import java.util.List;

public class SharedResourceLockScheduling {

	List<String> list;
	int inturn = 1;
	boolean firstTime = true;
	
	public void pushpop() {
		list = new ArrayList<>(); 
		
		Thread pushA = new Thread(() ->  { // 1,7,13,19,..
			while (true) {
				synchronized (this) {
					if (firstTime) {
						while (inturn != 1) {
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						while ((inturn)%6 != 1) {
							try { 
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println("thread name : "+Thread.currentThread().getName()+" inturn : "+inturn); 
					this.list.add("A");
					inturn++;
					
					list.forEach(element -> {
						System.out.print(element+" ");
					});
					System.out.println(); 
					this.notifyAll();
			}
			}
		},"pushA");
		
		Thread pushB = new Thread(() ->  { //  2,8,14,20,..
			while (true) {
				synchronized (this) {
					if (firstTime) {
						while (inturn != 2) {
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						while ((inturn)%6 != 2) {
							try { 
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println("thread name : "+Thread.currentThread().getName()+" inturn : "+inturn);
					this.list.add("B");
					inturn++;
					list.forEach(element -> {
						System.out.print(element+" ");
					});
					System.out.println(); 
					this.notifyAll();
				}
			}
		},"pushB");
		
		Thread pushC = new Thread(() ->  { // 3,9,15,21...
			while (true) {
				synchronized (this) {
					if (firstTime) {
						while (inturn != 3) {
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						while ((inturn)%6 != 3) {
							try { 
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println("thread name : "+Thread.currentThread().getName()+" inturn : "+inturn);
					this.list.add("C");
					inturn++;
					list.forEach(element -> {
						System.out.print(element+" ");
					});
					System.out.println(); 
					this.notifyAll();
			 }
		}
	},"pushC");
		
		
		  Thread popA = new Thread(() -> { // 6,12,18,...
			  while (true) {
				  synchronized (this) { 
					  while ((inturn)%6 != 0) {
							this.firstTime = false;
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("thread name : "+Thread.currentThread().getName()+" inturn : "+inturn);
						this.list.remove(list.lastIndexOf("A")); 
						inturn++;
						list.forEach(element -> {
							System.out.print(element+" ");
						});
						System.out.println(); 
						this.notifyAll(); 
				  } 
			}
		},"popA");
		  
		  Thread popB = new Thread(() -> { // 5,11,17,23,29,...
			  while (true) {
				  synchronized (this) { 
					  if (firstTime) {
							while (inturn != 5) {
								try { 
									this.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						} else {
							while ((inturn)%6 != 5) {
								try { 
									this.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						System.out.println("thread name : "+Thread.currentThread().getName()+" inturn : "+inturn);
						this.list.remove(list.lastIndexOf("B")); 
						inturn++;
						list.forEach(element -> {
							System.out.print(element+" ");
						});
						System.out.println();
						this.notifyAll();
				  } 
			  }
		},"popB");
		  
		  Thread popC = new Thread(() -> { // 4,10,16,22,28,34
			  while (true) {
				  synchronized (this) { 
					  if (firstTime) {
							while (inturn != 4) {
								try {
									this.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						} else {
							while ((inturn)%6 != 4) {
								try { 
									this.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						System.out.println("thread name : "+Thread.currentThread().getName()+" inturn : "+inturn);

						this.list.remove(list.lastIndexOf("C")); 
						inturn++;
						list.forEach(element -> {
							System.out.print(element+" ");
						});
						System.out.println();
						this.notifyAll(); 
				  }
			  }
		},"popC");
		
		pushA.setPriority(6);
		pushB.setPriority(5);
		pushC.setPriority(4);
		popC.setPriority(3);
		popB.setPriority(2);
		popA.setPriority(1); 
		pushA.start();
		pushB.start();
		pushC.start();
		popA.start(); 
		popB.start();
		popC.start();
 }
}
