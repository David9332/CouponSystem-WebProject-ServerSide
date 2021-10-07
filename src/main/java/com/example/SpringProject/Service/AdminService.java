package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.UserErrorException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    /**
     * Adds a company to th DB. Checks first if it already exists there (by name
     * or email), and adds it only if it does not.
     *
     * @param company - A Company object.
     * @throws UserErrorException - When there is already a company with this name or email.
     */
    void addCompany(Company company) throws UserErrorException;

    /**
     * Adds a list of companies to the DB. Checks if they already exists there (by name
     * and email), and adds them only if they don't.
     *
     * @param companies - A list of companies.
     * @throws UserErrorException - When there is already a company with this name or email.
     */
    void addCompaniesListToDB(List<Company> companies) throws UserErrorException;

    /**
     * Updates a company in the DB. First checks that the company exists in the DB (by companyId
     * and name), then replaces the old Company object with a new one.
     *
     * @param company - A Company object.
     * @throws UserErrorException - When the user tries to change the company's name (which
     *                            we do not allow).
     */
    void updateCompany(Company company) throws UserErrorException;

    /**
     * Deletes a Company object from the DB. First checks that the company exists in the DB (by id).
     *
     * @param companyID - The id of the company.
     * @throws UserErrorException - When there is no company with that id in the DB.
     */
    void deleteCompany(int companyID) throws UserErrorException;

    /**
     * Receives all the companies from the data base and puts them in a list.
     *
     * @return - A list of Company objects.
     */
    List<Company> getAllCompanies();

    /**
     * Receiving and printing the details of a specific company in the data base (by id).
     *
     * @param companyID - The id of the company.
     * @return - A specific company from the Data base.
     */
    Optional<Company> getOneCompany(int companyID) throws UserErrorException;

    /**
     * Adds a Customer Object to the DB. First checks that it does not already exists there (by email).
     *
     * @param customer - A Customer object.
     * @throws UserErrorException - When there's already a customer with that email in the DB.
     */
    void addCustomer(Customer customer) throws UserErrorException;

    /**
     * Adds a list of customers to the DB. Checks if they already exists there (by email),
     * and adds them only if they don't.
     *
     * @param customers - A list of customers.
     * @throws UserErrorException - When there's already a customer with that email.
     */
    void addCustomersListToDB(List<Customer> customers) throws UserErrorException;

    /**
     * Receives a Customer object from the user, Checks if the customer exists in the data base
     * (by email and password), and if it does - updates it by the given customer object.
     *
     * @param customer - A Customer object. Given by the user.
     * @throws UserErrorException - The customer does not exists in the data base.
     */
    void updateCustomer(Customer customer) throws UserErrorException;

    /**
     * Checks if a customer exists at the data base (by id), and if it does - deletes it.
     *
     * @param customerID - The id of the customer.
     * @throws UserErrorException - There is no customer with that id.
     */
    void deleteCustomer(int customerID) throws UserErrorException;

    /**
     * Receive all customers from the data base to a list.
     *
     * @return - A list of Customer objects.
     */
    List<Customer> getAllCustomers();

    /**
     * Receive one customer (by id).
     *
     * @param customerID - The id of the customer.
     * @return - A Customer object.
     * @throws UserErrorException - There is no customer with that id.
     */
    Optional<Customer> getOneCustomer(int customerID) throws UserErrorException;

    List<Coupon> getCompanyCoupons(int companyId) throws UserErrorException;

    List<Coupon> getCustomerCoupons(int customerId) throws UserErrorException;
}
