package net.fab.the.chemist.springbootrestfullws.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	//1)
	
	@GetMapping("V1/person")
	public  PersonV1 personV1() {
		return new PersonV1("John");
	}

	
	@GetMapping("V2/person")
	public  PersonV2 personV2() {
		return new PersonV2(new Name("John", "Wayne"));
	}
	
	// 2)
	
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
	
	/// 3)
	//http://localhost:8080/person/header
		@GetMapping(value="person/header", headers="X-API-VERSION=1")
		public  PersonV1 headerV_1() {
			return new PersonV1("John");
		}

		//http://localhost:8080/person/header
		//passer des param dans le header 
		//  Key=X-API-VERSION value=2
		@GetMapping(value="person/header", headers="X-API-VERSION=2")
		public  PersonV2 headerV_2() {
			return new PersonV2(new Name("John", "Wayne"));
		}
	
	// 4)
		
		@GetMapping(value="person/produces", produces="application/vnd.company.app-v1+json")
		public  PersonV1 producesV_1() {
			return new PersonV1("John");
		}

		//http://localhost:8080/person/header
		//passer des param dans le header 
		//  Key=X-API-VERSION value=2
		@GetMapping(value="person/produces", produces="application/vnd.company.app-v2+json")
		public  PersonV2 producesV_2() {
			return new PersonV2(new Name("John", "Wayne"));
		}
		
}
