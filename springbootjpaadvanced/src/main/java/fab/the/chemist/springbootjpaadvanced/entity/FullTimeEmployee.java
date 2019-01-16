package fab.the.chemist.springbootjpaadvanced.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

	@Column(name="EM_SALARY")
	private BigDecimal salary;
	
	protected FullTimeEmployee() {
		super();
	}
	
	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	
}
