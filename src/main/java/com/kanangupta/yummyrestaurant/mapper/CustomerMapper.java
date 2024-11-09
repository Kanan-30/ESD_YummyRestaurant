package com.kanangupta.yummyrestaurant.mapper;

import org.springframework.stereotype.Service;

import com.kanangupta.yummyrestaurant.dto.CustomerRequest;
import com.kanangupta.yummyrestaurant.dto.CustomerResponse;
import com.kanangupta.yummyrestaurant.entity.Customer;


@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
