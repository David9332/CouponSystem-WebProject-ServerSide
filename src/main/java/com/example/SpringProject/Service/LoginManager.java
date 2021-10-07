package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class LoginManager {
    private final AdminImpl adminImpl;
    private final CompaniesImpl companiesImpl;
    private final CustomersImpl customersImpl;

    /**
     * A login method that checks if the user had typed the right login details. directing the request
     * to the login methods at the facades. For a good login - returns a service object. For a wrong
     * login - returns null.
     *
     * @param email      - The email of the user.
     * @param password   - The password of the user.
     * @param clientType - The type of client (administrator, company or customer).
     * @return - adminImpl, companiesImpl or customersImpl objects if the login had succeeded.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    public ClientService login(String email, String password, ClientType clientType) throws UserErrorException {
        switch (clientType) {
            case ADMINISTRATOR:
                adminImpl.login(email, password);
                return adminImpl;

            case COMPANY:
                companiesImpl.login(email, password);
                return companiesImpl;

            case CUSTOMER:
                customersImpl.login(email, password);
                return customersImpl;

            default:
                return null;
        }
    }
}