package com.TodayLearning.springboot.restapistartcopy1.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>
{
     public List<UserDetails> findByRole(String role);  // here naming convension matters 
}
