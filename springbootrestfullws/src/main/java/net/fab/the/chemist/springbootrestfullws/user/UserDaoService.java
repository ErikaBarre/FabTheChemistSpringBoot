package net.fab.the.chemist.springbootrestfullws.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	public static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == 0) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUserByID(int id) {
		Iterator<User> iterator = UserDaoService.users.iterator();
		
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				//UserDaoService.users.remove(user);
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	

}