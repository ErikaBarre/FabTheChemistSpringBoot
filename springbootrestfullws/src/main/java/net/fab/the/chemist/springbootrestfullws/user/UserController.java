package net.fab.the.chemist.springbootrestfullws.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.hateoas.EntityLinks;;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable(name="id") int id) {
		User user = service.findById(id);
		if(user == null) {
			throw new NotFoundUserException("wrong data : object not found for this id :" + id);
		}
		return user;
	}
	
	@GetMapping("/users2/{id}")
	public User retrieveUser2(@PathVariable(name="id") int id) {
		User user = service.findById(id);
		
		/*
		    import org.springframework.hateoas.EntityModel;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

    EntityModel<User> model = new EntityModel<>(user.get());
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    model.add(linkTo.withRel("all-users"));
		
		*/
		//Resource<User> resource = new Resource<User>(user);
		//ControllerLinkBuilder linkTo = LinkedTransferQueue<E>
		
		//import org.springframework.hateoas.EntityModel;
	    //import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//	    EntityModel<User> model = new EntityModel<>(user.get());
//	    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//	    model.add(linkTo.withRel("all-users"));
		
		if(user == null) {
			throw new NotFoundUserException("wrong data : object not found for this id :" + id);
		}
		return user;
	}
	
	//
	// input - details of user
	// output - CREATED & Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = service.save(user);
		
		//on crée l'url de création du user
		// où "/{id}" aura pour valuer savedUser.getId()
		//cette url est renvoyée au client pour lui indiqué que tout s'est bien passé
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); 
		
		return ResponseEntity.created(location).build();
		//le but est de renvoyé un status 201 (status indiquant qu'un enregistrement est créé et que ça s'est bien passé)
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User deleteUser = service.deleteUserByID(id);
		
		if(deleteUser == null) {
			throw new NotFoundUserException("wrong data : object not found for this id :" + id);
		}
	}

	
	
}








