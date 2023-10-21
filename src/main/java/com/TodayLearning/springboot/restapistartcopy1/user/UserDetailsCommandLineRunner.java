package com.TodayLearning.springboot.restapistartcopy1.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner{

	
	Logger logger =LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDetailsRepository repository;
	
	
	public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info(Arrays.toString(args));
		
		repository.save(new UserDetails("Amit", "Admin"));
		repository.save(new UserDetails("Ravi","HR"));
		repository.save(new UserDetails("Harsh", "Admin"));
		//save method defined in CRUD repository and extendes by JPA 
		// save is used to save, update an existing  
		
		
		
		List<UserDetails> users=repository.findAll();
		users.forEach(user->logger.info(user.toString()));  //Note: for this step an empty constructor of userdetais needed there 
	/* 
	 If you didn't have default constructor then following issue comes : hibernate 
	 
	 Caused by: org.hibernate.InstantiationException: No default constructor for entity
	 */
	
	}

}
