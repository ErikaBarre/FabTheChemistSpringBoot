package fab.the.chemist.springbootjpaadvanced.repository;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fab.the.chemist.springbootjpaadvanced.entity.Employee;
import fab.the.chemist.springbootjpaadvanced.entity.FullTimeEmployee;
import fab.the.chemist.springbootjpaadvanced.entity.PartTimeEmployee;

@Transactional
@Repository
public class EmployeeRepository {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	PassportRepository passportRepository;
	
	public void insert(Employee employee) {
		entityManager.persist(employee);
	}
	
	public List<Employee> findAll(){
		//en jpql la table correspond à l'identifiant de classe
		return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
	}
	
	/**
	 * si c'est l'annotation @MappedSuperclass qui est utilisée dans la classe Employee (car la methode ci-dessus ne fonctionnera plus)
	 * @return
	 */
	public List<PartTimeEmployee> findAll0(){
		//en jpql la table correspond à l'identifiant de classe
		return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
	/**
	 * si c'est l'annotation @MappedSuperclass qui est utilisée dans la classe Employee (car la methode ci-dessus ne fonctionnera plus)
	 * @return
	 */
	public List<FullTimeEmployee> findAll1(){
		//en jpql la table correspond à l'identifiant de classe
		return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
	
	
	public Employee findById(long id){
		return entityManager.find(Employee.class, id);
	}
	
	
	
}
