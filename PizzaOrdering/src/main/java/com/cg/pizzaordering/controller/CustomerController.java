package com.cg.pizzaordering.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cg.pizzaordering.dto.Store;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.Pizza;
import com.cg.pizzaordering.exception.PizzaException;
//import com.cg.pizzaordering.repository.CartRepository;
import com.cg.pizzaordering.repository.CustomerRepository;
import com.cg.pizzaordering.repository.PizzaRepository;
import com.cg.pizzaordering.service.IAdminService;
import com.cg.pizzaordering.service.ICustomerService;
import com.cg.pizzaordering.service.Validate;

/*
 * Author : Bhrigu Garg
 */

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
@Transactional
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	@Autowired
	IAdminService adminService;
	@Autowired
	HttpSession session;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	PizzaRepository pizzaRepo;
//	@Autowired
//	CartRepository cartRepo;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	/**
	 * Register a new user
	 * 
	 * @param customer
	 * @return
	 */
	@PostMapping("/register")
	public Customer register(@RequestBody Customer customer) {
		logger.trace("In register Customer Controller");
		customerService.register(customer);
		return customer;
	}

	/**
	 * Get all cities
	 * 
	 * @param customer
	 * @return List of Store
	 */

	@GetMapping("/getCities")
	public List<Store> getStoreList() {
		logger.trace("getCities from Customer Controller");
		return customerService.getStoreList();
	}



	
	
	
	@PostMapping("/makecart")
	public ResponseEntity<?> makeCart(@RequestParam("username") String username,
			 @RequestBody Pizza pizza)

	{
		logger.trace("In makeCart()");
		try {
//			System.out.println(pizza.toString());
//			System.out.println(username);
			Customer customer = customerService.getCustomer(username);
//			System.out.println(customer.toString());
//			pizza =  customerService.getPizza(pizza);
			customer.setPizza(pizza);
//			System.out.println(customer.toString());
			customerRepo.save(customer);

			

			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (Exception e) {
//			System.out.println(e);
//			System.out.println("fgbfg");
			logger.trace("Exception caught in makeCart()");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping("/updatecart")
	public ResponseEntity<?> updateCart(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestBody Pizza pizza)

	{
		logger.trace("In makeCart()");
		try {
//			System.out.println("1");
			Customer customer = customerService.getCustomer(username);
			pizza =  customerService.getPizza(pizza);
			customer.setPizza(pizza);
			customerRepo.save(customer);

			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);

			logger.trace("Exception caught in makeCart()");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Cancel Cart
	 * 
	 * @param cartId
	 * @return boolean
	 */

	@PostMapping("/cancelcart")
	public boolean cancelCart(@RequestParam("username") String username, @RequestParam("password") String password) {
		logger.trace("In cancelCart()");
		try {
//			customerService.cancelCart(username, password);
			customerService.cancelCart(username, password);
		} catch (Exception e) {
		}
		return true;
	}

}
