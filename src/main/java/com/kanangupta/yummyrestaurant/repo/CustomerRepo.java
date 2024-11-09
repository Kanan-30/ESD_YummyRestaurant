package com.kanangupta.yummyrestaurant.repo;

import com.kanangupta.yummyrestaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
