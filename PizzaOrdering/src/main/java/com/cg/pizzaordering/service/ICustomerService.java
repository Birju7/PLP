package com.cg.pizzaordering.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//import com.cg.pizzaordering.dto.Cart;
import com.cg.pizzaordering.dto.Store;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.Pizza;
import com.cg.pizzaordering.exception.PizzaException;

public interface ICustomerService {
	
	public boolean register(Customer customer);
	
	public int authenticateUser(String username, String password);
	
	public List<Store> getStoreList();
	
//	public List<Pizza> availableRooms(LocalDate checkin, LocalDate checkout, Long storeId);
	
	public List<Pizza> showPizza(Long storeId) throws PizzaException;
	
//	public List<Room> showRoom(Long storeId, Long hotelId) throws PizzaException;
	
//	public Customer makeCart(Customer customer);
	
	public Customer getCustomer(String username);
	public Pizza getPizza(Pizza pizza);

//	boolean isAvailable(Room room, LocalDate checkIn, LocalDate checkOut);
	
	public boolean cancelCart(String username, String password);

	
}

