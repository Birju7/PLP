/*
 * 
 * 
 */

package com.cg.pizzaordering.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pizzaordering.dto.Store;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.Pizza;
import com.cg.pizzaordering.exception.PizzaException;
import com.cg.pizzaordering.exception.ResourceNotFoundException;
import com.cg.pizzaordering.exception.UserErrorMessage;
import com.cg.pizzaordering.repository.StoreRepository;
import com.cg.pizzaordering.repository.CustomerRepository;
import com.cg.pizzaordering.repository.PizzaRepository;

@Service("adminService")
@Transactional
public class AdminService implements IAdminService {
	@Autowired
	StoreRepository storerepo;
	@Autowired
	PizzaRepository pizzarepo;
	@Autowired
	CustomerRepository customerRepo;
	
	
	private final static String storeNotFound = "Store not found with Id: ";
	private final static String pizzaNotFound = "Pizza not found with Id: ";
	private final static String roomNotFound = "Room not found with Id: ";
	
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

	
	//-------------Store-------------------------
	
	/**
	 * Returns a list containing all the cities
	 */
	@Override
	public List<Store> showStore() { 
		List<Store> storeList = storerepo.findAll();
		logger.info("showStore method");
		return storeList;
	}
	
	/**
	 * Find store by storeId
	 */
	@Override
	public Store findStoreById(Long storeId) {
		//First check if store with given id exists
		Store store = storerepo.findById(storeId).orElse(null);
		//If store not found
		if(store==null) {
			logger.error("Store not found in findStoreById method");
			throw new ResourceNotFoundException(storeNotFound+storeId);
		}
		logger.info("FindStoreById method");
		return store;
	}
	
	/**
	 * Add a new store in the database
	 */
	@Override
	public Store addStore(Store store) {
		//First validate if store contains only alphabets
		//Will throw an exception if invalid
		Validate.isStringOnlyAlphabet(store.getStoreName());
		List<Store> storeList = storerepo.findAll();
		for(Store store2:storeList) {
			if(store2.getStoreName().equalsIgnoreCase(store.getStoreName()))
				throw new PizzaException("Store name already exists");
		}
		//Add store
		storerepo.save(store);
		logger.info("Store addded");
		return store;
	}

	/**
	 * Delete the store from the database
	 */
	@Override
	public boolean removeStore(Long storeId) {
		//First check if store with given id exists
		Store store = storerepo.findById(storeId).orElse(null);
		//If store not found
		if(store==null) {
			logger.error("Store not found in removeStore method");
			throw new ResourceNotFoundException(storeNotFound+storeId);
		}
		//Delete store
		storerepo.deleteById(storeId);
		logger.info("Store removed with Id: "+storeId);
		return true;
	}
	
	
	/**
	 * Update pizza
	 */
	@Override
	public boolean updateStore(Long storeId, Store store) {
		
		//First check if pizza with given id exists
		Store storeTemp = storerepo.findById(storeId).orElse(null);
		//If pizza not found
		if(storeTemp==null) {
			logger.error("Store not found in updateStore method");
			throw new ResourceNotFoundException(storeNotFound+storeId);
		}
		//If it exists set the Id of the updated pizza object and save it
//		store.setPizzaId(pizzaTemp.getPizzaId());
//		store.setStore(pizzaTemp.getStore());
//		pizzarepo.save(pizza);
//		logger.info("Pizza updated with Id: "+pizzaId);
		storerepo.save(store);
		return true;
	}	
	

	
	//-------------Pizza-------------------------
	
	/**
	 * Returns a list of all the pizzas in a store
	 */
	@Override
	public List<Pizza> showPizzas(Long storeId) {
		//First check if store with given id exists
		Store store = storerepo.findById(storeId).orElse(null);

		return store.getPizzaList();
	}
	
	/**
	 * Returns a pizza based on the pizzaId
	 */
	@Override
	public Pizza viewSinglePizza(Long pizzaId) {
		//First check if pizza with given id exists
		Pizza pizza = pizzarepo.findById(Long.valueOf(pizzaId)).orElse(null);
		//If pizza not found
		if(pizza==null) {
			logger.error("Pizza not found in viewSinglePizza method");
			throw new ResourceNotFoundException(pizzaNotFound+pizzaId);
		}
		return pizza;
	}
	
	/**
	 * Add a new pizza in the database in existing store
	 */
	@Override
	public boolean addPizza(Long storeId, Pizza pizza){	
		//Validate fields
		//Throws exception if mobile number is not valid
//		Validate.validateMobileNumber(pizza.getPizzaPhoneNumber());
		
		//First check if store with given id exists
		Store store = storerepo.findById(storeId).orElse(null);
		//If store not found
		if(store==null) {
			
			logger.error("Store not found in addPizza method");
			throw new ResourceNotFoundException(storeNotFound+storeId);
		}
		else {
			//Retrieve pizzaList from store
			List<Pizza> pizzaList = store.getPizzaList();
			//Add pizza to list and set the updated list in store
			pizzaList.add(pizza);
			pizza.setStore(store);
//			pizzarepo.save(pizza);
			store.setPizzaList(pizzaList);
			//Set store in pizza for Bidirectional mapping
			//Save store
			storerepo.save(store);
			//pizzarepo.save(pizza);
			logger.info("Pizza added");
			return true;
		}
	}
	
	/**
	 * Remove pizza from the database
	 */
	@Override
	public boolean removePizza(Long pizzaId) {
		//First check if pizza with given id exists
		Pizza pizza = pizzarepo.findById(pizzaId).orElse(null);
		//If pizza not found
		if(pizza==null) {
			logger.error("Pizza not found in removePizza method");
			throw new ResourceNotFoundException(pizzaNotFound+pizzaId);
		}
		//delete pizza
		pizzarepo.deleteById(pizzaId);
		logger.info("Pizza deleted with Id: "+pizzaId);
		return true;
	}

	/**
	 * Update pizza
	 */
	@Override
	public boolean updatePizza(Long pizzaId, Pizza pizza) {
		//First check if pizza with given id exists
		Pizza pizzaTemp = pizzarepo.findById(pizzaId).orElse(null);
		//If pizza not found
		if(pizzaTemp==null) {
			logger.error("Pizza not found in updatePizza method");
			throw new ResourceNotFoundException(pizzaNotFound+pizzaId);
		}
		//If it exists set the Id of the updated pizza object and save it
		pizza.setPizzaId(pizzaTemp.getPizzaId());
		pizza.setStore(pizzaTemp.getStore());
		pizzarepo.save(pizza);
		logger.info("Pizza updated with Id: "+pizzaId);
		return true;
	}	



	
	//-------------Authentication-------------------------
	
	
	
	@Override
	public int authenticateUser(String username, String password) {
		List<Customer> userList=customerRepo.findAll();
		for(Customer c:userList)
		{
			if((c.getUsername()==username)&&(c.getPassword()==password))
			{
				if (c.getRole().equalsIgnoreCase("Admin"))
					return 1;
				else
					return 0;
			}
		}
		return -1;
	}

	@Override
	public Customer findUser(String userEmail) throws PizzaException {
		Optional<Customer> customer =  customerRepo.findByEmailId(userEmail);
//		System.out.println(customer.toString());
		if(customer.isPresent()) {
			return customer.get();
		}
		throw new PizzaException(UserErrorMessage.userErrorInvalidEmailId);
	}
}