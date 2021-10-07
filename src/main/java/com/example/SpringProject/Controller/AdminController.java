package com.example.SpringProject.Controller;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Beans.UserDetails;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.AdminImpl;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Service.CustomersImpl;
import com.example.SpringProject.Utills.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("CouponsSystem/admin")
@RequiredArgsConstructor
public class AdminController {

    private final JWTutil jwTutil;
    private final AdminImpl adminImpl;
    private final CompaniesImpl companiesImpl;
    private final CustomersImpl customersImpl;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) throws UserErrorException {
        if (adminImpl.login(userDetails.getEmail(), userDetails.getPassword())) {
            String myToken = jwTutil.generateToken(new UserDetails(userDetails.getEmail(), 0, ClientType.ADMINISTRATOR));
            return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAllCompanies")
    public ResponseEntity<?> getCompanies(@RequestHeader(name = "Authorization") String token) {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(adminImpl.getAllCompanies());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getOneCompany/{companyId}")
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(adminImpl.getOneCompany(companyId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(adminImpl.getAllCustomers());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getOneCustomer/{customerId}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(adminImpl.getOneCustomer(customerId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("addCompany")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            adminImpl.addCompany(company);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            adminImpl.addCustomer(customer);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("updateCompany")
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            adminImpl.updateCompany(company);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            adminImpl.updateCustomer(customer);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("deleteCompany/{companyId}")
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            adminImpl.deleteCompany(companyId);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("deleteCustomer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            adminImpl.deleteCustomer(customerId);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCompanyCoupons/{companyId}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(adminImpl.getCompanyCoupons(companyId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("getCustomerCoupons/{customerId}")//http://localhost:8080/coupons/getCouponsByCustomer2/
    public ResponseEntity<?> getCouponsByCustomer2(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.CUSTOMER)))
                    .body(customersImpl.getCustomerCoupons(customerId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getAllCoupons")
    public List<Coupon> getAllCoupons() {
        return adminImpl.getAllCoupons();
    }
}
