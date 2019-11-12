package com.cg.hotelmanagement.HotelManagement;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.pizzaordering.controller.AdminController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	//How to test as some functions have request param
	
	@Autowired
	AdminController adminController;
	
//	@Test
//	public void testgetcities() {
//		assertEquals(3, adminController.getCities().size());
//	}
//	
//	@Test
//	public void testgetcity() {
//		Store city = restTemplate.getForObject("/cities",Store.class);
//		assertThat(city);
//	}
//	
//	@Test
//	public void testaddCity() {
//		Store city = restTemplate.getForObject("/cities/1", Store.class);
//		assertThat(city);
//	}
	
	//How to know which mapping as all url's are same

}
