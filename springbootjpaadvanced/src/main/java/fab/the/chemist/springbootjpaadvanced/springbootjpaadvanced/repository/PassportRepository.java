package fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.repository;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity.Passport;
@Transactional
@Repository
public class PassportRepository {

	@Autowired
	EntityManager entityManager;
	
	public Passport findById(long id){
		return entityManager.find(Passport.class, id);
	}
	
	public Passport save(Passport passport){
		if(passport.getId() == null) {
			entityManager.persist(passport);
		}else {
			entityManager.merge(passport);  // a noter que merge fait également un insert s'il ne trouve pas l'object
		}
		/*ces ligne de code sont equivalentes à cett§e ligne
		 entityManager.merge(passport);		 
		 */
		
		return passport;
	}
	
	

	public void deleteById(long id){
		Passport passport = findById(id);
		entityManager.remove(passport);
	}
	
	
}
