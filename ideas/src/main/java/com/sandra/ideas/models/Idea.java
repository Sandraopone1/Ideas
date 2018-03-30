package com.sandra.ideas.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Idea {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int likes;

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Size(min=1, message = "name name must be present")
	private String name;
	
	@Size(min=1, message="Creator name must be present")
	private String creator;
	
	@NotNull
	private int studentLimit;

	 @ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
			name = "users_ideas",
			joinColumns = @JoinColumn(name = "idea_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getLimit() {
		return studentLimit;
	}

	public void setLimit(Integer limit) {
		this.studentLimit = limit;
	}

	public int getStudentLimit() {
		return studentLimit;
	}

	public void setStudentLimit(int studentLimit) {
		this.studentLimit = studentLimit;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
