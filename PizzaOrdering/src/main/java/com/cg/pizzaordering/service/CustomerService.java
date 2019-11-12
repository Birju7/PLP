package com.cg.pizzaordering.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cg.pizzaordering.dto.Cart;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.Pizza;
import com.cg.pizzaordering.dto.Store;
import com.cg.pizzaordering.exception.PizzaException;
//import com.cg.pizzaordering.repository.CartRepository;
import com.cg.pizzaordering.repository.CustomerRepository;
import com.cg.pizzaordering.repository.PizzaRepository;
import com.cg.pizzaordering.repository.StoreRepository;

@Service("customerService")
@Transactional

/*
 * Author : Avantika Singh/
 */

public class CustomerService implements ICustomerService {

	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	StoreRepository storeRepo;

	
	@Autowired
	PizzaRepository pizzaRepo;
//	@Autowired
//	CartRepository cartRepo;

	/**
	 * Register a new user
	 */
	@Override
	public boolean register(Customer customer) {
//		if (!Validate.aadhar(customer.getAadharNumber()))
//			throw new PizzaException("Length of aadhar must be 12");

		// Validate password and throws exception if invalid
//		Validate.validatePassword(customer.getPassword());

		// validateMobileNumber throws exception if mobile number is invalid
//		Validate.validateMobileNumber(customer.getUserMobile());

		// To validate if username is unique
		List<Customer> list = customerRepo.findAll();
		for (Customer customer2 : list) {
			if (customer2.getUsername().equals(customer.getUsername()))
				throw new PizzaException("Username should be unique");
		}

		customerRepo.save(customer);
		return true;
	}

	/**
	 * Authenticate a user and check his role
	 */
	@Override
	public int authenticateUser(String username, String password) {

		System.out.println("parameter");
		System.out.println(username);
		System.out.println(password);
//		Customer user = getCustomer(username, password);
		Customer user = getCustomer(username);
		System.out.println("attribute");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		// Changed else to else if
		if (user != null) {
			if (user.getRole().equalsIgnoreCase("Admin"))
				return 1;
			else if (user.getRole().equalsIgnoreCase("User"))
				return 0;
		}

		return -1;
	}

	/**
	 * Return a list of cities
	 */
	@Override
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return storeRepo.findAll();
	}

	
	/*
	 * Returns List of all Hotels/
	 */

	public List<Pizza> showPizza(Long storeId) throws PizzaException {
		List<Store> storeList = storeRepo.findAll();

		for (Store c : storeList) {
			if (c.getStoreId() == storeId)
				return c.getPizzaList();
		}

		return null;
	}
	
	

	
	@Override
	public boolean cancelCart(String username, String password) {
		List<Customer> customerList = customerRepo.findAll();
		Customer customer = customerRepo.findByUsername(username).orElse(null);
		customer.setPizza(null);
		customerRepo.save(customer);
		return true;

	}

	@Override
	public Customer getCustomer(String username) {
		System.out.println("In getrCustomer");
		// TODO Auto-generated method stub
		Customer customer = customerRepo.findByEmailId(username).orElse(null);
//		System.out.println(customer);
//		if (customer.getPassword().equals(password)) {
			return customer;
//		} else {
//			return null;
//		}

	}
	
	@Override
	public Pizza getPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		pizza = pizzaRepo.findById(pizza.getPizzaId()).orElse(null);
//		if (customer.getPassword().equals(password)) {
			return pizza;
//		} else {
//			return null;
//		}

	}
}
