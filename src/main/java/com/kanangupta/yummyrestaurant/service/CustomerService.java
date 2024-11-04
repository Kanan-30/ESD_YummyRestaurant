package com.kanangupta.yummyrestaurant.service;

import com.kanangupta.yummyrestaurant.dto.CustomerRequest;
import com.kanangupta.yummyrestaurant.dto.CustomerResponse;
import com.kanangupta.yummyrestaurant.entity.Customer;
import com.kanangupta.yummyrestaurant.mapper.CustomerMapper;
import com.kanangupta.yummyrestaurant.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}