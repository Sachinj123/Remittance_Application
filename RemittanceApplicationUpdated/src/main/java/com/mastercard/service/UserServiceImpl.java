package com.mastercard.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mastercard.dto.UserDTO;
import com.mastercard.model.User;
import com.mastercard.repository.UserRepository;
import com.mastercard.resourceNotFound.UserNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * @Bean public ModelMapper getmodelMapper() { return new ModelMapper(); }
	 */
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public User saveUser(UserDTO userDTO) {
		/*
		 * User user = new User( userDTO.getFullName(), userDTO.getEmail(),
		 * userDTO.getDateOfBirth(), userDTO.getPhoneNumber(),
		 * userDTO.getAddressLine1(), userDTO.getAddressLine2(), userDTO.getCity(),
		 * userDTO.getCountry(),userDTO.getSecurityAnswer(),userDTO.getSecurityQuestion(
		 * ), userDTO.getPincode(), userDTO.getUserRole(),
		 * passwordEncoder.encode(userDTO.getPassword()));
		 */
		User user = modelMapper.map(userDTO,User.class);
		passwordEncoder.encode(userDTO.getPassword());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return  userRepository.findUserByEmail(username);
	}

	@Override
	public User updateUser(UserDTO userDTO) {
		
		/*
		 * User user = new User(userDTO.getFullName(), userDTO.getEmail(),
		 * userDTO.getDateOfBirth(), userDTO.getPhoneNumber(),
		 * userDTO.getAddressLine1(), userDTO.getAddressLine2(), userDTO.getCity(),
		 * userDTO.getCountry(),userDTO.getSecurityAnswer(),userDTO.getSecurityQuestion(
		 * ), userDTO.getPincode(), userDTO.getUserRole(),
		 * passwordEncoder.encode(userDTO.getPassword()));
		 */
		User user = modelMapper.map(userDTO, User.class);
		return userRepository.save(user);
	}

	@Override
	public String deleteUserById(int id) {
		userRepository.deleteById(id);
		return "User Deleted Successfully with Id:"+id;
	}

	@Override
	public User findUserById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElseThrow(()-> new UserNotFoundException("User Not Found with ID:"+id));
	}

	@Override
	public List<User> getUserByUserRole(String userRole) {
		return userRepository.findUserByUserRole(userRole);
	}

	@Override
	public void updateUserRole(String userEmail, String newRole) {
		User user = userRepository.findUserByEmail(userEmail);
		if(user != null)
		{
			user.setUserRole(newRole);
			userRepository.save(user);
		}
		
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}




}
