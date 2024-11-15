package com.kanangupta.yummyrestaurant.Controller;


import com.kanangupta.yummyrestaurant.dto.CustomerRequest;
import com.kanangupta.yummyrestaurant.dto.CustomerResponse;
import com.kanangupta.yummyrestaurant.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kanangupta.yummyrestaurant.dto.LoginRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
/*public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustoemr(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}*/

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String token = customerService.login(request);
        return ResponseEntity.ok(token);
    }

    /*New Functionalities*/
    @GetMapping
    public ResponseEntity<CustomerResponse> getCustomerDetails(@RequestHeader("Authorization") String authHeader) {
        String email = customerService.validateAndExtractEmail(authHeader);
        return ResponseEntity.ok(customerService.getCustomerDetails(email));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String authHeader,
                                                 @RequestBody CustomerRequest request) {
        String email = customerService.validateAndExtractEmail(authHeader);
        customerService.updateCustomer(email, request);
        return ResponseEntity.ok("Customer details updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String authHeader) {
        String email = customerService.validateAndExtractEmail(authHeader);
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("Customer account deleted successfully");
    }


}





