package net.fab.the.chemist.springbootrestfullws.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

//import org.springframework.hateoas.EntityModel;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserJpaResource {

	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable(name="id") int id) {
		//optional : si cela retourne null ou pas, l'object sera "proper"
		Optional<User> user = userRepository.findById(id);
		//on ne teste pas si l'objet est null mais si ispresent car c'est un proper objet
		if(!user.isPresent()) {
			throw new NotFoundUserException("wrong data : object not found for this id :" + id);
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		
		//ce sont des info qu'on peut envoyer au client (ne fonctionne pas ???) :
		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUser())  ;
		//resource.add(linkTo.withRel(linkTo.withRel("all-users")));
		
		return resource;
	}
	
	@GetMapping("/jpa/users2/{id}")
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
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);
		
		//on crée l'url de création du user
		// où "/{id}" aura pour valeur savedUser.getId()
		//cette url est renvoyée au client pour lui indiqué que tout s'est bien passé
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); 
		
		return ResponseEntity.created(location).build();
		//le but est de renvoyé un status 201 (status indiquant qu'un enregistrement est créé et que ça s'est bien passé)
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
		
		
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post>  retrieveAllUsers(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		//on ne teste pas si l'objet est null mais si ispresent car c'est un proper objet
		if(!user.isPresent()) {
			throw new NotFoundUserException("wrong data : object not found for this id :" + id);
		}
		
		return user.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostUser(@Valid @RequestBody int id, @RequestBody Post post){
		Optional<User> ouser = userRepository.findById(id);
		//on ne teste pas si l'objet est null mais si ispresent car c'est un proper objet
		if(!ouser.isPresent()) {
			throw new NotFoundUserException("wrong data : object not found for this id :" + id);
		}
		
		
		
		User user = ouser.get();
		
		//pas nécessaire
		//List<Post> posts = new ArrayList<>();
		//posts.add(post);
		//user.setPosts(posts);
		
		post.setUser(user);
		Post p =postRepository.save(post);
		
		//pas nécessaire
		//User savedUser = userRepository.save(entity)(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri(); 
		
		return ResponseEntity.created(location).build();
		//le but est de renvoyé un status 201 (status indiquant qu'un enregistrement est créé et que ça s'est bien passé)
		
	}
	
}








