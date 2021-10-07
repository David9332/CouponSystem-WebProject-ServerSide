package com.example.SpringProject.Controller;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Beans.UserDetails;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CustomersImpl;
import com.example.SpringProject.Utills.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("CouponsSystem/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final CustomersImpl customersImpl;
    private final JWTutil jwTutil;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) throws UserErrorException {
        if (customersImpl.login(userDetails.getEmail(), userDetails.getPassword())) {
            String myToken = jwTutil.generateToken(new UserDetails(userDetails.getEmail(), customersImpl.getCustomerLoggedIn().getCustomerId(), ClientType.CUSTOMER));
            return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCustomerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.CUSTOMER)))
                    .body(customersImpl.getCustomerCoupons(customersImpl.getCustomerLoggedIn().getCustomerId()));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCustomerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader(name = "Authorization") String token, @RequestBody Category category) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.CUSTOMER)))
                    .body(customersImpl.getCustomerCouponsByCategory(category));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCustomerDetails")
    public ResponseEntity<?> getCustomerLoggedIn(@RequestHeader(name = "Authorization") String token) {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(customersImpl.getCustomerLoggedIn());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCustomerCouponsByMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.CUSTOMER)))
                    .body(customersImpl.getCustomerCouponsByMaxPrice(customersImpl.getCustomerLoggedIn().getCustomerId(), maxPrice));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("purchaseCoupon/{couponId}")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws CouponStockException, UserErrorException {
        if (jwTutil.validateToken(token)) {
            customersImpl.purchaseCoupon(couponId);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.CUSTOMER)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("purchaseCartCoupons")
    public ResponseEntity<?> purchaseCartCoupons(@RequestHeader(name = "Authorization") String token, @RequestBody List<Coupon> coupons) throws CouponStockException, UserErrorException {
        if (jwTutil.validateToken(token)) {
            customersImpl.purchaseCartCoupons(coupons);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.CUSTOMER)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
