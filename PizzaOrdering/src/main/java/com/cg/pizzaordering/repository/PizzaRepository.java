package com.cg.pizzaordering.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.pizzaordering.dto.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {

}
