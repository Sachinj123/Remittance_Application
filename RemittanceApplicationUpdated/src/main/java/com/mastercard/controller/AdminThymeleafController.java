package com.mastercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.model.AmountTransferConfigurationPage;
import com.mastercard.service.IAmountTransferConfigurationPageService;
import com.mastercard.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminThymeleafController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAmountTransferConfigurationPageService amountTransferConfigurationPageService;
	
	private boolean isAdmin(Authentication authentication)
	{
		return authentication.getAuthorities()
				.stream().anyMatch(role -> role.getAuthority().equals("ADMIN"));
		
	}
	
	@GetMapping("/editUserRole")
	public String editUserRoleForm()
	{
		return "editUserRole";
	}
	
	
	@PostMapping("/updateUserRole")
	public String updateUserRole(@RequestParam String userEmail,@RequestParam String newRole , Authentication authentication)
	{
		if(isAdmin(authentication))
		{
			userService.updateUserRole(userEmail, newRole);
			return "success";
		}
		else
		{
			return "accessDenied";
		}
	}
	
	
}
