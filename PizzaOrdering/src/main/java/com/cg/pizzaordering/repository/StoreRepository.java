package com.cg.pizzaordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.pizzaordering.dto.Store;



public interface StoreRepository extends JpaRepository<Store,Long> {

}
