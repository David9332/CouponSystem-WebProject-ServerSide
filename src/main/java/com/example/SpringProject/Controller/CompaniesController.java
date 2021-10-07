package com.example.SpringProject.Controller;


import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.UserDetails;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Utills.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CouponsSystem/companies")
@RequiredArgsConstructor
public class CompaniesController {

    private final JWTutil jwTutil;
    private final CompaniesImpl companiesImpl;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) throws UserErrorException {
        if (companiesImpl.login(userDetails.getEmail(), userDetails.getPassword())) {
            String myToken = jwTutil.generateToken(new UserDetails(userDetails.getEmail(), companiesImpl.getCompanyLoggedIn().getCompanyId(), ClientType.COMPANY));
            return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCouponsByMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(companiesImpl.getCompanyCouponsByMaxPrice(maxPrice));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCouponsByCategory/{category}")
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader(name = "Authorization") String token, @RequestParam Category category) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(companiesImpl.getCompanyCouponsByCategory(category));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCouponsByCompany")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(companiesImpl.getCompanyCoupons());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization") String token) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.ADMINISTRATOR)))
                    .body(companiesImpl.getCompanyDetails());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("addCoupon")
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            companiesImpl.addCoupon(coupon);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("updateCoupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            companiesImpl.updateCoupon(coupon);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("deleteCoupon/{couponId}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws UserErrorException {
        if (jwTutil.validateToken(token)) {
            companiesImpl.deleteCoupon(couponId);
            return ResponseEntity.ok()
                    .header("Authorization", jwTutil.generateToken(new UserDetails(
                            jwTutil.extractEmail(token),
                            ClientType.COMPANY)))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
