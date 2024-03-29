package com.allycode;

import com.allycode.customer.Customer;
import com.allycode.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication

public class Main {


    public static void main(String[] args) {
        ConfigurableApplicationContext
                applicationContext = SpringApplication.run(Main.class, args);

}

@Bean
CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer alex = new Customer(
                    "Alex",
                    "alex@gmal.com",
                    21

            );

            Customer jamila = new Customer(
                    "jamila",
                    "jamila@gmal.com",
                    19

            );

            List<Customer> customers = List.of(alex, jamila);
            customerRepository.saveAll(customers);
        };
}
    private static void printBeans(ConfigurableApplicationContext ctx) {
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
