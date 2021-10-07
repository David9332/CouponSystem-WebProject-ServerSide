package com.example.SpringProject.Repositories;

import com.example.SpringProject.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepo extends JpaRepository<Customer, Integer> {

    /**
     * Gets a customer by email.
     *
     * @param email - The email of the customer.
     * @return - A Customer object.
     */
    Customer findByEmail(String email);

    /**
     * Gets a customer by email and password.
     *
     * @param email - The email of the customer.
     * @param password - The password of the customer.
     * @return - A Customer object.
     */
    Customer findByEmailAndPassword(String email, String password);

}
