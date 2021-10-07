package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;

import java.sql.SQLException;
import java.util.List;

public interface CustomersService {

    /**
     * Gets a coupon from the data base (by id), checks that:
     * 1. It wasn't already purchased by the customer.
     * 2. That there are more the 0 coupons of that kind.
     * 3. That its end-date had not already passed.
     * If all the above are ture, it adds a purchase and decreases the amount of coupons by 1.
     *
     * @param couponId   - The id of the coupon.
     * @throws UserErrorException   - 1. coupon already purchased by that customer.
     *                              2. There is no customer with that id.
     * @throws CouponStockException - 1. There are no more coupons of that kind in our stock.
     *                              2. The end date of this coupon has already passed
     */
    void purchaseCoupon(int couponId) throws UserErrorException, CouponStockException;

    void purchaseCouponForCreatingDB(int customerId, int couponId) throws UserErrorException, CouponStockException;

    void purchaseCartCoupons(List<Coupon> coupons) throws UserErrorException, CouponStockException;

    /**
     * Gets from the data base all the coupons that were purchased by a specific customer and
     * puts them into a list.
     *
     * @param customerID - The id of the customer.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    List<Coupon> getCustomerCoupons(int customerID) throws UserErrorException;

    /**
     * Gets from the data base all the coupons that were purchased by a specific customer
     * that belongs to a specific category, and puts them into a list.
     *
     * @param customerID - The id of the customer.
     * @param category   - A Category enum.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    List<Coupon> getCustomerCouponsByCategory(Category category) throws UserErrorException;

    /**
     * Gets all the coupons of a specific customer, under or equal to a given price, from the data base,
     * and puts them into a list.
     *
     * @param customerID - The id of the customer.
     * @param maxPrice   - The price's upper limit.
     * @return - A list of Customer objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    List<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice) throws UserErrorException;

    /**
     * Gets a customer from the data base, and prints its details.
     *
     * @param customerID - The id of the customer.
     * @throws UserErrorException - When there is no customer with that id.
     */
    void showCustomerDetails(int customerID) throws UserErrorException;
}
