package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.UserErrorException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    public abstract boolean login(String email, String password) throws UserErrorException;

}
