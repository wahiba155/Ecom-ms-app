package org.moussaoui.billingservice.web;

import org.moussaoui.billingservice.entities.Bill;
import org.moussaoui.billingservice.feign.CustomerServiceRestClient;
import org.moussaoui.billingservice.feign.InventoryServiceRestClient;
import org.moussaoui.billingservice.model.Customer;
import org.moussaoui.billingservice.repository.BillRepository;
import org.moussaoui.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;
    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerServiceRestClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(inventoryServiceRestClient.getProduct(pi.getProductId()));
        });
        return bill;

    }
}
