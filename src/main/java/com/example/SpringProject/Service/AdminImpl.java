package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Repositories.CustomersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminImpl extends ClientService implements AdminService {
    private final CompaniesRepo companiesRepo;
    private final CustomersRepo customersRepo;
    private final CouponsRepo couponsRepo;

    @Override
    public boolean login(String email, String password) throws UserErrorException {
        String adminEmail = "admin@admin.com";
        String adminPassword = "admin";
        if (!(adminEmail.equals(email) && adminPassword.equals(password))) {
            throw new UserErrorException("* Login failed. You either typed wrong details, or this account doesn't exist.");
        }
        return true;
    }

    /**
     * Adds a company to th DB. Checks first if it meets the validation conditions, then checks if if
     * already exists there (by name or email), and adds it only if it does not.
     *
     * @param company - A Company object.
     * @throws UserErrorException - When there is already a company with this name or email.
     */
    @Override
    public void addCompany(Company company) throws UserErrorException {
        String couponStringId = String.valueOf(company.getCompanyId());
//        if (couponStringId.contentEquals(" ")) {
//            throw new UserErrorException("* Please leave the id field empty. Id's are given by the system.");
//        }
        if (company.getName().length() < 3) {
            throw new UserErrorException("* The name of the company must be at least 2 notes long." +
                    " \nAdding failed.");
        }
        if (company.getPassword().length() < 10) {
            throw new UserErrorException("* The password of the company must be at least 10 notes long." +
                    " \nAdding failed.");
        }
        if (company.getEmail().length() < 10) {
            throw new UserErrorException("* The email of the company must be at least 10 notes long." +
                    " \nAdding failed.");
        }
        if (companiesRepo.findByName(company.getName()) != null) {
            throw new UserErrorException("* There's already a company with that name in the DB." +
                    " \nAdding failed.");
        }
        if (companiesRepo.findByEmail(company.getEmail()) != null) {
            throw new UserErrorException("* There's already a company with that email in the DB." +
                    " \nAdding failed.");
        }
        //company.getCoupons().forEach(couponsRepo::saveAndFlush);
        companiesRepo.save(company);
    }

    /**
     * Adds a list of companies to the DB. Checks first if it meets the validation conditions, then Checks if they already exists there (by name
     * and email), and adds them only if they don't.
     *
     * @param companies - A list of companies.
     * @throws UserErrorException - When there is already a company with this name or email.
     */
    @Override
    public void addCompaniesListToDB(List<Company> companies) throws UserErrorException {
        for (Company company : companies) {
            if (company.getName().length() < 3) {
                throw new UserErrorException("* The name of the company must be at least 2 notes long." +
                        " \nAdding failed.");
            }
            if (company.getPassword().length() < 10) {
                throw new UserErrorException("* The password of the company must be at least 10 notes long." +
                        " \nAdding failed.");
            }
            if (company.getEmail().length() < 10) {
                throw new UserErrorException("* The email of the company must be at least 10 notes long." +
                        " \nAdding failed.");
            }
            if (companiesRepo.findByName(company.getName()) != null) {
                throw new UserErrorException("* There's already a company with the name '"
                        + company.getName() + "' in the DB.");
            }
            if (companiesRepo.findByEmail(company.getEmail()) != null) {
                throw new UserErrorException("* There's already a company with the email '"
                        + company.getName() + "' in the DB.");
            }
            companiesRepo.save(company);
        }
    }

    /**
     * Updates a company in the DB. Checks first if it meets the validation conditions, then checks that the
     * company exists in the DB (by companyId and name), then replaces the old Company object with a new one.
     *
     * @param company - A Company object.
     * @throws UserErrorException - When the user tries to change the company's name (which
     *                            we do not allow).
     */
    @Override
    public void updateCompany(Company company) throws UserErrorException {
        if ((companiesRepo.findByCompanyIdAndName(company.getCompanyId(), company.getName())) == null) {
            throw new UserErrorException("* You can't edit the company's id and name. Updating failed.");
        }
        if (company.getName().length() < 3) {
            throw new UserErrorException("* The name must be at least 2 notes long." +
                    " \nUpdating failed.");
        }
        if (company.getPassword().length() < 10) {
            throw new UserErrorException("* The password must be at least 10 notes long." +
                    " \nUpdating failed.");
        }
        if (company.getEmail().length() < 10) {
            throw new UserErrorException("* The email must be at least 10 notes long." +
                    " \nUpdating failed.");
        }
        companiesRepo.save(company);
    }

    /**
     * Deletes a Company object from the DB. First checks that the company exists in the DB (by id).
     *
     * @param companyID - The id of the company.
     * @throws UserErrorException - When there is no company with that id in the DB.
     */
    @Override
    public void deleteCompany(int companyID) throws UserErrorException {
        Company company = companiesRepo.findById(companyID).orElseThrow(() -> new UserErrorException(
                "* The company you are trying to delete does not exist in the DB. Delete failed."));
        company.getCoupons().forEach(coupon -> couponsRepo.deleteByCouponId(coupon.getCouponId()));
        companiesRepo.deleteById(companyID);
    }

    /**
     * Gets all companies in the DB into a list.
     *
     * @return - A list of companies.
     */
    @Override
    public List<Company> getAllCompanies() {
        return companiesRepo.findAll();
    }

    /**
     * Gets a specific Company Object from the DB (by companyId).
     *
     * @param companyID - The id of the company.
     * @return - A single Company object.
     * @throws UserErrorException - When there is not company with this id.
     */
    @Override
    public Optional<Company> getOneCompany(int companyID) throws UserErrorException {
        if (!companiesRepo.existsById(companyID)) {
            throw new UserErrorException("* There is no company with this id.");
        }
        return companiesRepo.findById(companyID);
    }

    /**
     * Adds a Customer Object to the DB. Checks first if it meets the validation conditions, then
     * checks that it does not already exists there (by email).
     *
     * @param customer - A Customer object.
     * @throws UserErrorException - When there's already a customer with that email in the DB.
     */
    @Override
    public void addCustomer(Customer customer) throws UserErrorException {
        if (customer.getFirstName().length() < 3) {
            throw new UserErrorException("* The first name must be at least 2 notes long." +
                    " \nAdding failed.");
        }
        if (customer.getLastName().length() < 3) {
            throw new UserErrorException("* The last name must be at least 2 notes long." +
                    " \nAdding failed.");
        }
        if (customer.getEmail().length() < 10) {
            throw new UserErrorException("* The email must be at least 10 notes long." +
                    " \nAdding failed.");
        }
        if (customer.getPassword().length() < 10) {
            throw new UserErrorException("* The password must be at least 10 notes long." +
                    " \nAdding failed.");
        }
        if (customersRepo.findByEmail(customer.getEmail()) != null) {
            throw new UserErrorException("* There's already a customer with that email in the DB. Adding failed.");
        }
        customersRepo.save(customer);
    }

    /**
     * Adds a list of customers to the DB. Checks first if it meets the validation conditions, then Checks if they
     * already exists there (by email), and adds them only if they don't.
     *
     * @param customers - A list of customers.
     * @throws UserErrorException - When there's already a customer with that email.
     */
    @Override
    public void addCustomersListToDB(List<Customer> customers) throws UserErrorException {
        for (Customer customer : customers) {
            if (customer.getFirstName().length() < 3) {
                throw new UserErrorException("'AdminImpl'", "'addCustomersListToDB'", "The first name must be at least 2 notes long." +
                        " \nAdding failed.");
            }
            if (customer.getLastName().length() < 3) {
                throw new UserErrorException("'AdminImpl'", "'addCustomersListToDB'", "The last name must be at least 2 notes long." +
                        " \nAdding failed.");
            }
            if (customer.getEmail().length() < 10) {
                throw new UserErrorException("'AdminImpl'", "'addCustomersListToDB'", "The email must be at least 10 notes long." +
                        " \nAdding failed.");
            }
            if (customer.getPassword().length() < 10) {
                throw new UserErrorException("'AdminImpl'", "'addCustomersListToDB'", "The password must be at least 10 notes long." +
                        " \nAdding failed.");
            }
            if (customersRepo.findByEmail(customer.getEmail()) != null) {
                throw new UserErrorException("'AdminImpl'", "'addCustomersListToDB'", "There's already a customer with the email." + customer.getEmail() + " in the DB. Adding was not done.");
            }
            customersRepo.save(customer);
        }
    }

    /**
     * Receives a Customer object from the user, first Checks if the customer exists in the data base then (by
     * and password), then checks if it meets the validation conditions, and if it does - updates it by
     * the given customer object.
     *
     * @param customer - A Customer object. Given by the user.
     * @throws UserErrorException - The customer does not exists in the data base.
     */
    @Override
    public void updateCustomer(Customer customer) throws UserErrorException {
        if (!customersRepo.existsById(customer.getCustomerId())) {
            throw new UserErrorException("* There is no customer with this id in the DB. Updating failed.");
        }
        if (customer.getFirstName().length() < 3) {
            throw new UserErrorException("* The first name must be at least 2 notes long." +
                    " \nAdding failed.");
        }
        if (customer.getLastName().length() < 3) {
            throw new UserErrorException("* The last name must be at least 2 notes long." +
                    " \nAdding failed.");
        }
        if (customer.getEmail().length() < 10) {
            throw new UserErrorException("* The email must be at least 10 notes long." +
                    " \nAdding failed.");
        }
        if (customer.getPassword().length() < 10) {
            throw new UserErrorException("* The password must be at least 10 notes long." +
                    " \nAdding failed.");
        }
        customersRepo.save(customer);
    }

    /**
     * Checks if a customer exists at the data base (by id), and if it does - deletes it.
     *
     * @param customerID - The id of the customer.
     * @throws UserErrorException - There is no customer with that id.
     */
    @Override
    public void deleteCustomer(int customerID) throws UserErrorException {
        if (!customersRepo.existsById(customerID)) {
            throw new UserErrorException("* The customer you are trying to delete does not exist in the DB. No deleting was done.");
        }
        customersRepo.deleteById(customerID);
    }

    /**
     * Gets all the customers in the data base into a list.
     *
     * @return - A list of Customer objects.
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customersRepo.findAll();
    }

    /**
     * Receive one customer (by id).
     *
     * @param customerId - The id of the customer.
     * @return - A Customer object.
     * @throws UserErrorException - There is no customer with that id.
     */
    @Override
    public Optional<Customer> getOneCustomer(int customerId) throws UserErrorException {
        if (!customersRepo.existsById(customerId)) {
            throw new UserErrorException("There is no customer with this id.");
        }
        return customersRepo.findById(customerId);
    }

    /**
     * Gets all the coupons of a specific company from the data base (by company id) and puts them
     * into a list.
     *
     * @return - A list of Coupon objects.
     * @throws UserErrorException - There is no company with this id.
     */
    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws UserErrorException {
        Company company = companiesRepo.findById(companyId).orElseThrow(() -> new UserErrorException("* There is no company with that id."));
        return couponsRepo.findByCompanyId(companyId);
    }

    /**
     * Gets from the data base all the coupons that were purchased by a specific customer and
     * puts them into a list.
     *
     * @param customerId - The id of the customer.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws UserErrorException {
        Customer customer = customersRepo.findById(customerId).orElseThrow(() -> new UserErrorException("* There is no customer with that id."));
        return customer.getCoupons();
    }

    public List<Coupon> getAllCoupons() {
        return couponsRepo.findAll();
    }
}
