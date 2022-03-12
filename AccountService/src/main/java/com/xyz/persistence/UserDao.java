package com.xyz.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xyz.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
	@Query("from User where customerName=?1 and customerPassword=?2")
	public User findUserByUserNameAndPassword(String username, String password);
	
	@Query("from User where customerEmail=?1")
	public User findUserByEmail(String userEmail);
	
	@Query("from User where customerName=?1")
	public User findUserByUserName(String username);
}
