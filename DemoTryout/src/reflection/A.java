package reflection;

public class A {
	private String a1;
	private String a2;
	
	public A() {
		super();
	}

	public A(String a1, String a2) {
		super();
		this.a1 = a1;
		this.a2 = a2;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	@Override
	public String toString() {
		return "A [a1=" + a1 + ", a2=" + a2 + "]";
	}

}
