package reflection;

public class AB {
	
	private String a1;
	private String a2;
	private int b1;
	private int b2;
	
	public AB() {
		super();
	}

	public AB(String a1, String a2, int b1, int b2) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.b1 = b1;
		this.b2 = b2;
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

	public int getB1() {
		return b1;
	}

	public void setB1(int b1) {
		this.b1 = b1;
	}

	public int getB2() {
		return b2;
	}

	public void setB2(int b2) {
		this.b2 = b2;
	}

	@Override
	public String toString() {
		return "AB [a1=" + a1 + ", a2=" + a2 + ", b1=" + b1 + ", b2=" + b2 + "]";
	}

}