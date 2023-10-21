package com.TodayLearning.springboot.restapistartcopy1.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

}
