package com.allycode.customer;

import com.allycode.exception.DuplicateResourceException;
import com.allycode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }
    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        //Check if email exists
        String email = customerRegistrationRequest.email();
        if (customerDao.existsPersonWithEmail(email)) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()

        );
        customerDao.insertCustomer(
                customer
        );
    }

    public void deleteCustomerById(Integer customerId) {
        if (!customerDao.existsPersonWithId(customerId)) {
            throw new ResourceNotFoundException(
                    "customer with id [%s] not found".formatted(customerId)
            );
        }
        customerDao.deleteCustomerById(customerId);
    }
}
