package com.TodayLearning.springboot.restapistartcopy1.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserDetails 
{
   private Long id;
   private String name;
   private String role;
   
   
   //added default constructor to avoid the hibernate issue 
   public UserDetails()
   {
	   super();
   }
   
   
   
   public UserDetails(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
   
   
   
   
@Id
@GeneratedValue
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

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}



@Override
public String toString() {
	return "UserDetails [id=" + id + ", name=" + name + ", role=" + role + "]";
}
   
   

   
}
