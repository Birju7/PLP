package com.cg.pizzaordering.service;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.UserDetailsImpl;
import com.cg.pizzaordering.exception.ExistingCredentialException;
import com.cg.pizzaordering.exception.UserErrorMessage;
import com.cg.pizzaordering.repository.CustomerRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder brcyptEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Customer> user = customerRepository.findByEmailId(userEmail);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with email: " + userEmail);
		}
		return user.map(UserDetailsImpl::new).get();
	}
	
	public Customer save(Customer customer) throws ExistingCredentialException {
//		User newUser = new User(brcyptEncoder.encode(user.getUserPassword()), user.getUserName(), user.getContactNo(), user.getUserEmail(), user.getAge(), user.getGender());
//		return userRepository.save(newUser);
		
		logger.info("Checking if the email is already registered..");
		// Validating unique database columns
		Optional<Customer> checkUserCredentials = customerRepository.findByEmailId(customer.getEmailId());
		if (checkUserCredentials.isPresent()) {
			logger.error("An existing account with this email found... throwing ExistingCredentialException");
			throw new ExistingCredentialException(UserErrorMessage.userErrorDuplicateEmail);
		} else {
			checkUserCredentials = null;
			logger.info("Email is unique. Checking if the phone number is already registered..");
			checkUserCredentials = customerRepository.findByUserMobile(customer.getUserMobile());
			if (checkUserCredentials.isPresent()) {
				logger.error("An existing account with this contact found... throwing ExistingCredentialException");
				throw new ExistingCredentialException(UserErrorMessage.userErrorDuplicatePhoneNumber);
			}
		}
		// save if email and phone numbers are unique
		logger.info("Phone number is also unique. Registering the user..");
		customer.setPassword(brcyptEncoder.encode(customer.getPassword()));
		customer.setRole("ROLE_USER");
		System.out.println("1"+customer.toString());
		return customerRepository.save(customer);
		
	}
	
}