package com.example.OrderService.external.client;

import com.example.OrderService.exception.CustomException;
import com.example.OrderService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {
    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    default  ResponseEntity<Long> fallback(Exception e){
        throw new CustomException("Payment Service is not accessible","UNAVAILABLE",500);
    }
}


