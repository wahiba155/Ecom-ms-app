package org.moussaoui.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.moussaoui.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customer-service", fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long id);
    default Customer getDefaultCustomer(Long id, Exception exception){
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName("NOT AVAILABLE");
            customer.setEmail("NOT AVAILABLE");

             return customer;
    }

    @GetMapping("/customers")
    PagedModel<Customer> getAllCustomers();

}
