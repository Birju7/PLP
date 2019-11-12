package com.cg.pizzaordering.controller;
import java.io.Console;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pizzaordering.configuration.JwtTokenUtil;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.JwtRequest;
import com.cg.pizzaordering.dto.JwtResponse;
import com.cg.pizzaordering.dto.UserDetailsImpl;
import com.cg.pizzaordering.exception.ExistingCredentialException;
import com.cg.pizzaordering.service.AdminService;
//import com.cg.pizzaordering.service.CustomerService;
import com.cg.pizzaordering.service.JwtUserDetailsService;



@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("1");
		System.out.println(authenticationRequest.getEmailId() +" "+ authenticationRequest.getPassword());
		authenticate(authenticationRequest.getEmailId(), authenticationRequest.getPassword());
		System.out.println("2");
		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername(authenticationRequest.getEmailId());

		final String token = jwtTokenUtil.generateToken(userDetails);
System.out.println(userDetails.toString());

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping(value = "/register")
	
	public ResponseEntity<String> saveUser(@RequestBody Customer user) {
	//	return ResponseEntity.ok(jwtUserDetailsService.save(user));
		
		try {
			return new ResponseEntity<String>(jwtUserDetailsService.save(user).toString(), HttpStatus.OK);
		}catch(ExistingCredentialException exception) {
			logger.error(exception.getMessage());
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}