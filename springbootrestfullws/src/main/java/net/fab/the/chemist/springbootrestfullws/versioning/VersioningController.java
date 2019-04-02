package net.fab.the.chemist.springbootrestfullws.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	@GetMapping("V1/person")
	public  PersonV1 personV1() {
		return new PersonV1("John");
	}

	
	@GetMapping("V2/person")
	public  PersonV2 personV2() {
		return new PersonV2(new Name("John", "Wayne"));
	}
	
	
	
	//http://localhost:8080/person/param?version=1
	@GetMapping(value="person/param", params="version=1")
	public  PersonV1 paramV1() {
		return new PersonV1("John");
	}

	//http://localhost:8080/person/param?version=2
	@GetMapping(value="person/param", params="version=2")
	public  PersonV2 paramV2() {
		return new PersonV2(new Name("John", "Wayne"));
	}
	
}
