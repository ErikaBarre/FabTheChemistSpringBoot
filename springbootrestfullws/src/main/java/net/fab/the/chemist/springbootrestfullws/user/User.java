package net.fab.the.chemist.springbootrestfullws.user;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("all details about users") //swagger
@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue
	private int id;

	//verification de la taille, il faut qu'il y ait @Valid dans le controller pour activer
	@ApiModelProperty(notes="Name should have content 2 characters")
	@Size(min=2, message="Name should have content 2 characters")
	private String name;

	@ApiModelProperty(notes="Date has to be in past") 
	@Past(message="Date has to be in past")
	private Date birthDate;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	protected User() {
		
	}
	
	public User(int id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}