package com.mastercard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomeSuccessHandler customeSuccessHandler;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Bean
	public static PasswordEncoder getpasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(c -> c.disable()).authorizeHttpRequests
		(request -> request.requestMatchers("/admin-page")
				.hasAuthority("ADMIN")
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/profile").permitAll()
				.requestMatchers("/recipients/**").permitAll()
				.requestMatchers("/wallet/**").hasAuthority("USER")
				.requestMatchers("/user-page").hasAuthority("USER")
				.requestMatchers("/registration").permitAll()
				.requestMatchers("/user/**").permitAll()
				.requestMatchers("/updateUserRole").permitAll()
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated())
		
		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
				.successHandler(customeSuccessHandler).permitAll())
		
		.logout(form -> form.invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll());
		
		return http.build();	
	}
	
	
	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(customeUserDetailsService).passwordEncoder(getpasswordEncoder());
	}
}
