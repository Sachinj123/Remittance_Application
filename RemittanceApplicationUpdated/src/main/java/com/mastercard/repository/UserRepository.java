package com.mastercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mastercard.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findUserByEmail(String email);
	
	public List<User> findUserByUserRole(String userRole);
	
	
	
}
