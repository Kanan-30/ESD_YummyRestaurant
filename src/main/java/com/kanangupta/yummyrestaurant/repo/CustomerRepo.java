package com.kanangupta.yummyrestaurant.repo;

import com.kanangupta.yummyrestaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
