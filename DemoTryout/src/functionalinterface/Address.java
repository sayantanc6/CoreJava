package functionalinterface;

public class Address {
	
	private String country;

	public Address() {
		super();
	}

	public Address(String country) {
		super();
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + "]";
	}
	
	

}
