package fab.the.chemist.springbootaop.data;

import org.springframework.stereotype.Repository;

import fab.the.chemist.springbootaop.aspect.TrackTime;

@Repository
public class Dao {
	
	public String retrieveSomething() {
		return "dao1";
	}

	@TrackTime
	public String retrieveSomething2() {
		return "dao11";
	}
	
}
