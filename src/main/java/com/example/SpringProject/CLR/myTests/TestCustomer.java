package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Service.CustomersImpl;
import com.example.SpringProject.Service.LoginManager;
import com.example.SpringProject.Utills.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(3)
public class TestCustomer implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            CustomersImpl customersImpl = (CustomersImpl) loginManager.login("avi@gmail.com", "cvvkcvcddvk", ClientType.CUSTOMER);

            customersImpl.purchaseCouponForCreatingDB(1, 10);
            customersImpl.purchaseCouponForCreatingDB(2, 9);
            customersImpl.purchaseCouponForCreatingDB(3, 8);
            customersImpl.purchaseCouponForCreatingDB(4, 11);
            customersImpl.purchaseCouponForCreatingDB(4, 12);
            customersImpl.purchaseCouponForCreatingDB(4, 17);
            customersImpl.purchaseCouponForCreatingDB(4, 18);
            customersImpl.purchaseCouponForCreatingDB(4, 19);
            customersImpl.purchaseCouponForCreatingDB(5, 6);
            customersImpl.purchaseCouponForCreatingDB(6, 5);
            customersImpl.purchaseCouponForCreatingDB(7, 6);
            customersImpl.purchaseCouponForCreatingDB(7, 9);
            customersImpl.purchaseCouponForCreatingDB(7, 10);
            customersImpl.purchaseCouponForCreatingDB(7, 18);
            customersImpl.purchaseCouponForCreatingDB(7, 19);
            customersImpl.purchaseCouponForCreatingDB(8, 12);
            customersImpl.purchaseCouponForCreatingDB(9, 13);
            customersImpl.purchaseCouponForCreatingDB(10, 5);

            System.out.println(Art.sperator);
            System.out.println();
            System.out.println(Art.DATABASE_READY);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }
}
