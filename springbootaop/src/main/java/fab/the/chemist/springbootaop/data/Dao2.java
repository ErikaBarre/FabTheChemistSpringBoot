package fab.the.chemist.springbootaop.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
public class Dao2 {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public String retriveSomething() {
		return "dao2";
	}

}
