package fab.the.chemist.springbootjpa.dao;


import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import fab.the.chemist.springbootjpa.bean.Person;
import fab.the.chemist.springbootjpa.rowMapper.PersonRowMapper;

//BeanPropertyRowMapper
//ce mapper est un mapper par defaut il considerer que le nom de la classe est identique que le nom de la table 
//et que le nom des propriétés de la classes sont identiques aux identifiants de colonne de la table  
//dans notre cas, les méthode ci-dessous sont indiqué pour l'exemple mais ne fonctionne pas car la table est préfixée et les nom de colonnes également

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired 
	PlatformTransactionManager platformTransactionManager;
	
	public List<Person> findAllBasic() {
		return jdbcTemplate.query("select * from fab_person", new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public List<Person> findAll(){
		return jdbcTemplate.query("select * from fab_person", new PersonRowMapper());
	}
	
	public Person findByIdBasic(int id){
		return jdbcTemplate.queryForObject("select * from fab_person where id = ?",
				new Object[] {id},
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findById(int id){
		return jdbcTemplate.queryForObject("select * from fab_person where pe_id = ?",
				new Object[] {id},
				new PersonRowMapper());
	}
	
	/**
	 * 
	 * @param id
	 * @return le nombre d'enregistrements effacés
	 */
	public int deleteById(int id){
		return jdbcTemplate.update("delete from fab_person where pe_id = ?", new Object[] {id});
//		 return jdbcTemplate.update("delete from person where id = ?",
//				new Object[] {id},new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public int insert(Person person){
		return jdbcTemplate.update ("insert into fab_person (PE_ID,PE_NAME,PE_LOCATION,PE_BIRTH_DATE) values (?, ?, ?, ?)", 
						new Object[] {person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()) });
	}
		
	@org.springframework.transaction.annotation.Transactional
	public int updateById(Person person){
		return jdbcTemplate.update ("update fab_person set PE_NAME = ? , PE_LOCATION = ? , PE_BIRTH_DATE = ? where PE_ID = ?", 
						new Object[] {person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId() });
	}
	
//	public void commit(){
//		DefaultTransactionDefinition paramTransactionDefinition = new    DefaultTransactionDefinition();
//		TransactionStatus status= platformTransactionManager.getTransaction(paramTransactionDefinition );
//		System.out.println(status);
//		platformTransactionManager.commit(status);
//	}
	
}
