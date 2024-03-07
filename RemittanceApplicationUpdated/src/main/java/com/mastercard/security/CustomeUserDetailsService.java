package com.mastercard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mastercard.model.User;
import com.mastercard.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByEmail(email);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("Username with :"+email+" is not found in the Database"); 
		}
		
		return new CustomeUserDetails(user);
	}

}
