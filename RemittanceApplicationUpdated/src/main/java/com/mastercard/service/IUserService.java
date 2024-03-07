package com.mastercard.service;

import java.util.List;
import java.util.Optional;

import com.mastercard.dto.UserDTO;
import com.mastercard.model.User;

public interface IUserService {
	
	public User saveUser(UserDTO userDTO);
	
	public List<User> getAllUsers(); 
	
	public User findByUsername(String username);
	
	public User updateUser(UserDTO userDTO);
	
	public User findUserById(int id);
	
	public String deleteUserById(int id);
	
	public List<User> getUserByUserRole(String userRole);
	
	public void updateUserRole(String userEmail,String newRole);
	
	public User getUserByEmail(String email);
}
