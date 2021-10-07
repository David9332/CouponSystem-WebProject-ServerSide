package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CompaniesService {

    /**
     * Receives a Coupon object from the user, checks if the coupon already exists in the
     * data base, and if it doesn't - adds it to the data base and to the company's
     * coupons list.
     *
     * @param coupon - A Coupon object. Given by the user.
     * @throws UserErrorException - The coupon already exists in the data base.
     */
    void addCoupon(Coupon coupon) throws UserErrorException;

    /**
     * Adds a list of coupons to the DB, only if the coupons don't already exist there (checks by
     * companyId and title).
     *
     * @param coupons - A list of coupons.
     * @throws UserErrorException - When a coupon already exists at the DB.
     */
    void addCouponsListToDB(List<Coupon> coupons) throws UserErrorException;

    /**
     * Adds a list of coupons to a company. First checks that the company exists at the DB (by id)
     *
     * @param companyId - The id of the Company object.
     * @param coupons - A list of Coupons objects.
     * @throws UserErrorException   - 1. If there is no company with that id
     *                              - 2. If a coupon already exists at this company.
     * @throws CouponStockException - If the end date of this coupon has already passed.
     */
    void addCouponsListToCompany(int companyId, List<Coupon> coupons) throws UserErrorException, CouponStockException;

    /**
     * Receives a Coupon object from the user, checks if the coupon already exists in the
     * data base, and if it does - updates it by the coupon object.
     *
     * @param coupon - A coupon object. Given by the user.
     * @throws UserErrorException - The coupon does not exist in the DB.
     */
    void updateCoupon(Coupon coupon) throws UserErrorException;

    /**
     * Checks if a coupon exists in the data base (by id), and if it does - deletes it.
     *
     * @param couponID - The id of the coupon.
     * @throws UserErrorException - The coupon does not exist in the data base.
     */
    void deleteCoupon(int couponID) throws UserErrorException;

    /**
     * Deletes coupons by end-dates that had already passed.
     *
     * @param date - A Date object.
     */
    void deleteCouponByDate(Date date);

    /**
     * Gets all the coupons of a specific company from the data base (by company id) and puts them
     * into a list.
     *
     * @return - A list of Coupon objects.
     * @throws UserErrorException - The company does not exist in the data base.
     */
    List<Coupon> getCompanyCoupons() throws UserErrorException;

    /**
     * Gets all the coupons of a specific company, under or equal to a given price, from the data
     * base and puts them into a list.
     *
     * @param maxPrice  - The price's upper limit.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - The company does not exist in the data base.
     */
    List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws UserErrorException;

    /**
     * Gets all the coupons of a specific company by a specific category from the data base
     * (by company id and category) and puts them into a list.
     *
     * @param companyID - The id of the company.
     * @param category - The category of the coupon.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - The company does not exist in the data base.
     */
    List<Coupon> getCompanyCouponsByCategory(Category category) throws UserErrorException;

    /**
     * Receive a Company object from the data base(by id).
     *
     * @throws UserErrorException - The company does not exist in the data base.
     */
    Optional<Company> getCompanyDetails() throws UserErrorException;

}
