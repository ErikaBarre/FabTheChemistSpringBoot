package fab.the.chemist.springbootaop.data;

import org.springframework.stereotype.Repository;

@Repository
public class Dao {
	
	public String retrieveSomething() {
		return "dao1";
	}

}
