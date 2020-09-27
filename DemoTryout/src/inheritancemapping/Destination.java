package inheritancemapping;

public class Destination {
	private String firstname;
	private String lastname;
	private String firstSubname;
	private String lastSubname;
	
	public Destination() {
		super();
	}
	
//	@Mapping("firstname")
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
//	@Mapping("lastname")
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
//	@Mapping("firstSubname")
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
		return "MappedClass [firstname=" + firstname + ", lastname=" + lastname + ", firstSubname=" + firstSubname
				+ ", lastSubname=" + lastSubname + "]";
	}
}
