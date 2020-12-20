package dto.inheritancemapping;

public class Super {
	private String firstname;
	private String lastname;
	
	public Super() {
		super();
	}

	public Super(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	//@Override
	public String toString() {
		return "SuperClass [firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}
