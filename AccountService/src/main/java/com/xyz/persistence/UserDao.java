package com.xyz.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xyz.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
	
	//The login query. as Username is unique, makes sure that the input password matches the Username, if that Username exists.
	@Query("from User where customerName=?1 and customerPassword=?2")
	public User findUserByUserNameAndPassword(String username, String password);
	
	//Checks if customerEmail already exists in database.
	@Query("from User where customerEmail=?1")
	public User findUserByEmail(String userEmail);
	
	//Checks if customerName already exists in database
	@Query("from User where customerName=?1")
	public User findUserByUserName(String username);
}
