package withoutsso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {
	
	@Autowired
	MyBeanService servicebean;
	
	@RequestMapping(value ="/print")
	public void print() {
		servicebean.dostuff();
	}

}
