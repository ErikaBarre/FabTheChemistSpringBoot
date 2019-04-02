package net.fab.the.chemist.springbootrestfullws.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	@GetMapping(path="V1/person")
	public  PersonV1 personV1() {
		return new PersonV1("John");
	}

	
	@GetMapping("V2/person")
	public  PersonV2 personV2() {
		return new PersonV2(new Name("John", "Wayne"));
	}
	
}
