package com.umg.analisis.sistemas.service;
import com.umg.analisis.sistemas.payload.CustomerPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final CustomerService customerService;

    @Async("customerExecutor")
    public void createCustomer(CustomerPayload email) {
        customerService.createAndSend(email);
    }


}
