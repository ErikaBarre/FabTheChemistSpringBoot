package net.fab.the.chemist.springbootrestfullws.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static  List<User> users = new ArrayList<User>();
	
	private static int userCount = 3;
	
	static {
		users.add(new User(1, "John", new Date()));
		users.add(new User(2, "Jack", new Date()));
		users.add(new User(3, "Carl", new Date()));
		
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if( user.getId() == 0) {
			userCount++;
		}
		users.add(user);
		return user;
	}
	
	public User findById(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
		
	}
	
}
