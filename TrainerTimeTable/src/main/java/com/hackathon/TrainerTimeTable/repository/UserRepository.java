package com.hackathon.TrainerTimeTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.TrainerTimeTable.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	User findByEmail(String email);
	
	User findById(long id);

	User save(User user);
}
