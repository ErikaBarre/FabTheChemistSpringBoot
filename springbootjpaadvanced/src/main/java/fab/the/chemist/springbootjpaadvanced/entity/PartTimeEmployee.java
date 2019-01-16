package fab.the.chemist.springbootjpaadvanced.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	@Column(name="EM_HOURLY_WAGE")
	private BigDecimal hourlyWage;
	
	protected PartTimeEmployee() {
		super();
	}
	
	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
}
