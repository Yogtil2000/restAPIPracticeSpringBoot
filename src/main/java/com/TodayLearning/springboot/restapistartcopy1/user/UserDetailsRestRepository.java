package com.TodayLearning.springboot.restapistartcopy1.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
// you can do more configurations as well here by adding the annotaion and then path in it at which you want to expoosed the API 
// @RepositoryRestResource(path="")
public interface UserDetailsRestRepository extends PagingAndSortingRepository<UserDetails, Long>   // as this gives some additional advantages 
{
     public List<UserDetails> findByRole(String role);  // here naming convension matters 
}

/* 
http://localhost:8080/userDetailses  ===> this is the first URL 

after hitting it you can see the all details as well as some more things like paging 

As an additioal feature you can able to sort it as well like as below :

http://localhost:8080/userDetailses?sort=name
http://localhost:8080/userDetailses?sort=role

 ===> you are also able to set the page size there 
     suppose on the one page you just want an one user then "size=1"

     http://localhost:8080/userDetailses?size=1
          and there you also get the linkes of the next pages 
          
          
          you are also able to make the post request here simply :
              http://localhost:8080/userDetailses  ===> after the hitting of hit you get 201 ===>  great 
     
     

NOTE: if you quickly want to create the rest APIs then spring data rest is considered to be an alternative over there.......



*/