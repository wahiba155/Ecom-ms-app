
package org.moussaoui.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.moussaoui.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceRestClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "inventory-service", fallbackMethod = "getDefaultCustomer")
    Product getProduct(@PathVariable Long id);
    default Product getDefaultCustomer(Long id, Exception exception){

        return Product.builder().id(String.valueOf(id)).build();
    }

}