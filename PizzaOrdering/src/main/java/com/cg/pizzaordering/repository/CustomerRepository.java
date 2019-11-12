package com.cg.pizzaordering.repository;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

import com.cg.pizzaordering.dto.Customer;

import org.springframework.data.jpa.repository.JpaRepository;;



public interface CustomerRepository extends JpaRepository<Customer,Long> {
	public Customer findByEmailIdAndPassword(@Param("email")String email, @Param("password")String password);
	public Optional<Customer> findByUsername(String username);
	public Optional<Customer> findByEmailId(String customerEmail);
	public Optional<Customer> findByUserMobile(String contactNo);
	
	

}
