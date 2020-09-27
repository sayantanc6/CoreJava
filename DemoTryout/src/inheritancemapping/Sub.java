package inheritancemapping;

public class Sub extends Super {

	private String firstSubname;
	private String lastSubname;

	

	public Sub(String firstname, String lastname, String firstSubname, String lastSubname) {
		super(firstname, lastname);
		this.firstSubname = firstSubname;
		this.lastSubname = lastSubname;
	}


	public String getFirstSubname() {
		return firstSubname;
	}


	public void setFirstSubname(String firstSubname) {
		this.firstSubname = firstSubname;
	}
	
//	@Mapping("lastSubname")
	public String getLastSubname() {
		return lastSubname;
	}


	public void setLastSubname(String lastSubname) {
		this.lastSubname = lastSubname;
	}


	@Override
	public String toString() {
		return "SubClass [firstSubname=" + firstSubname + ", lastSubname=" + lastSubname + "]";
	}
	
}
