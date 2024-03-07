package com.mastercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.dto.UserDTO;
import com.mastercard.model.User;
import com.mastercard.service.IUserService;

@RestController
@RequestMapping("/user")
public class RemittanceUserController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws Exception
	{
		User users = userService.saveUser(userDTO);
		return ResponseEntity.ok().body(users);
	}
	
	@PutMapping("/update-user")
	public ResponseEntity<User> updateUserDetails(@RequestBody UserDTO userDTO) throws Exception
	{
		User user = userService.updateUser(userDTO);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/getAll")
	public List<User> getAllUsers()
	{
		return  userService.getAllUsers();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUseryById(@PathVariable Integer id) {
		 userService.deleteUserById(id);
	}
	
	@GetMapping("/getuser/{id}")
	public User getUserById(@PathVariable Integer id)
	{
		return userService.findUserById(id);
	}
	
}
