package streams;

public class Foo {
	
	private String f1;
	private String f2;
	
	public Foo() {
		super();
	}

	public Foo(String f1, String f2) {
		super();
		this.f1 = f1;
		this.f2 = f2;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	@Override
	public String toString() {
		return "Foo [f1=" + f1 + ", f2=" + f2 + "]";
	}


}
