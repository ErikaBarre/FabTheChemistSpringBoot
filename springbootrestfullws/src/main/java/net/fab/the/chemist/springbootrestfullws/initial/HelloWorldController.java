package net.fab.the.chemist.springbootrestfullws.initial;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	//@RequestMapping(path="/hello-world" , method=RequestMethod.GET)
	//EQUIVALENT A :
	@GetMapping(path="/hello-world")
	public String HelloWorld() {
		return "encore du blabla";
	}

	@GetMapping(path="/hello-world-bean")
	public HWBean HelloWorldBean() {
		return new HWBean("encore du blabla json");
	}
	
	///hello-world-bean/path-variable/trucname
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HWBean HelloWorldBeanPath(@PathVariable String name) {
		return new HWBean("encore du blabla json path: " + name);
	}
}



