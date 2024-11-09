package com.kanangupta.yummyrestaurant.service;

import com.kanangupta.yummyrestaurant.dto.CustomerRequest;
import com.kanangupta.yummyrestaurant.dto.CustomerResponse;
import com.kanangupta.yummyrestaurant.entity.Customer;
import com.kanangupta.yummyrestaurant.helper.JwtUtil;
import com.kanangupta.yummyrestaurant.mapper.CustomerMapper;
import com.kanangupta.yummyrestaurant.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import com.kanangupta.yummyrestaurant.dto.LoginRequest;

@Service
@RequiredArgsConstructor
/*public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}*/

public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.password()));
        customerRepo.save(customer);
        return "Customer created successfully";
    }

    public String login(LoginRequest request) {
        Optional<Customer> customerOptional = customerRepo.findByEmail(request.email());

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (passwordEncoder.matches(request.password(), customer.getPassword())) {
                return jwtUtil.generateToken(customer.getEmail());
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    public void deleteCustomer(Long customerId) {
        customerRepo.deleteById(customerId);
    }

    public String updateCustomer(Long customerId, CustomerRequest request) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customerRepo.save(customer);
        return "Customer updated successfully";
    }

}
