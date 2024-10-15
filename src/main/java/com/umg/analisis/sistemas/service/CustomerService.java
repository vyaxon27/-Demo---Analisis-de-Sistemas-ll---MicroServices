package com.umg.analisis.sistemas.service;

import com.umg.analisis.sistemas.document.Customer;
import com.umg.analisis.sistemas.payload.CustomerPayload;
import com.umg.analisis.sistemas.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void createAndSend(CustomerPayload payload) {
        Customer customer = new Customer();

        customer.setUserId(payload.getUserId());
        customer.setFirstName(payload.getFirstName());
        customer.setLastName(payload.getLastName());
        customer.setPhoneNumber(payload.getPhoneNumber());
        customer.setBirthDay(payload.getBirthDay());

        customerRepository.save(customer);
        log.info("user created -> email: {}", payload);
    }
}
