package net.fab.the.chemist.springbootrestfullws.user;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private UserDaoService userDaoService;
	
	public  List<User> retriveAllUsers(){
		return userDaoService.findAll();
	}
	
	public User retriveOneUsers(int id){
		return userDaoService.findById(id);
	}
	
	
}
