package com.cg.pizzaordering.service;

import java.util.List;

import com.cg.pizzaordering.dto.Store;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.Pizza;
import com.cg.pizzaordering.exception.PizzaException;

public interface IAdminService {

	public List<Store> showStore();
	
	public Store findStoreById(Long storeId);
	
	public Store addStore(Store store);

	public boolean removeStore(Long storeId);
	public boolean updateStore(Long storeId, Store store);
	
	

	
	public List<Pizza> showPizzas(Long cityId);
	
	public Pizza viewSinglePizza(Long pizzaId);
	
	public boolean addPizza(Long cityId, Pizza pizza);
	
	public boolean removePizza(Long pizzaId);
	
	public boolean updatePizza(Long pizzaId, Pizza pizza);
	
	
	
//	public List<Room> showRooms(Long cityId, Long pizzaId);
//	
//	public Room viewSingleRoom(long roomId);
//	
//	public Room addRoom(Long cityId, Long pizzaId,
//			Room room);
//
//	public boolean removeRoom(Long roomId);
//
//	public boolean updateRoom(Long roomId, Room room);
	
	
	public Customer findUser(String userEmail) throws PizzaException;
	public int authenticateUser(String username, String password);


	
	
	
	

}