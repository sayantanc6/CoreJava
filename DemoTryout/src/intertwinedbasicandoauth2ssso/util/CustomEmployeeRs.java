package intertwinedbasicandoauth2ssso.util;

import java.io.Serializable;
import java.util.Map;

public class CustomEmployeeRs implements Serializable {

	 private static final long serialVersionUID = -2883410526895516483L;

	    public static final String USERNAME = "firstName";
	    public static final String PASSWORD = "lastName";
	    public static final String ORGANIZATION_NAME = "departmentName";

	    private String userName;
	    private String password;
	    private String organizationName;

	    public CustomEmployeeRs(String userName, String lastName, String organizationName) {
	        this.userName = userName;
	        this.password = password;
	        this.organizationName = organizationName;
	    }

	    public CustomEmployeeRs(Map<String, Object> values) {
	        this.userName = values.get(USERNAME) != null ? (String) values.get(USERNAME): null;
	        this.password = values.get(PASSWORD) != null ? (String) values.get(PASSWORD) : null;
	        this.organizationName = values.get(ORGANIZATION_NAME) != null ? (String) values.get(ORGANIZATION_NAME) : null;
	    }

	    public CustomEmployeeRs() {
	    }
}
