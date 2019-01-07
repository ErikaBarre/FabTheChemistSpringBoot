package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//à tester avec commonjointpointsconfig.java

@Aspect
@Configuration
public class BeforeAspect2 { 

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//on prendre les paramètres sur base de ce qui se trouve dans commonjointpointsconfig
	
	@Before("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.dataLayerExecution()")
	public void before(JoinPoint joinpoint) {
		LOGGER.info("intercept method call {}", joinpoint);
	}
	
	@Before("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
	public void before2(JoinPoint joinpoint) {
		LOGGER.info("intercept method call {}", joinpoint);
	}
	
}
