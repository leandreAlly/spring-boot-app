package com.allycode.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessService  implements CustomerDao{

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();

        Customer alex = new Customer(
                1,
                "Alex",
                "alex@gmal.com",
                21

        );

        customers.add(alex);

        Customer jamila = new Customer(
                2,
                "jamila",
                "jamila@gmal.com",
                19

        );
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
       return  customers.stream().filter(c -> c.getId().equals(id))
                .findFirst();
    }
}
