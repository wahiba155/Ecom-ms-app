package org.moussaoui.customerservice;

import org.moussaoui.customerservice.entities.Customer;
import org.moussaoui.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository ){
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("wahiba").email("wahiba@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("ouma").email("ouma@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("bouchra").email("bouchra@gmail.com")
                    .build());

        };
    }

}
