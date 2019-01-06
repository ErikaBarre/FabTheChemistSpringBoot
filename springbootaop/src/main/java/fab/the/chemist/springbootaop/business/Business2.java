package fab.the.chemist.springbootaop.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fab.the.chemist.springbootaop.data.Dao2;

@Service
public class Business2 {
	
	@Autowired
	private Dao2 dao2;
	
	public String calculateSomething() {
		return  dao2.retriveSomething();
	}
	
}
