package fab.the.chemist.springbootaop.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fab.the.chemist.springbootaop.data.Dao;

@Service
public class Business {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Dao dao;
	
	public String calculateSomething(){
		//Business Logic
		String value = dao.retrieveSomething();
		logger.info("In Business - {}", value);
		return value;
	}
}