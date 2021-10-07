package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Repositories.CustomersRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CustomersImpl extends ClientService implements CustomersService {
    private final CustomersRepo customersRepo;
    private final CouponsRepo couponsRepo;
    private final CompaniesRepo companiesRepo;
    private static Customer customerLoggedIn;


    @Override
    public boolean login(String email, String password) throws UserErrorException {
        customerLoggedIn = customersRepo.findByEmailAndPassword(email, password);
        if (customerLoggedIn == null) {
            throw new UserErrorException("* Login failed. You either typed wrong details, or this account doesn't exist.");
        }
        return true;
    }

    /**
     * Gets a coupon from the data base (by id), checks that:
     * 1. It wasn't already purchased by the customer.
     * 2. That there are more the 0 coupons of that kind.
     * 3. That its end-date had not already passed.
     * If all the above are ture, it adds a purchase and decreases the amount of coupons by 1.
     *
     * @param couponId - The id of the coupon.
     * @throws UserErrorException   - 1. coupon already purchased by that customer.
     *                              2. There is no customer with that id.
     * @throws CouponStockException - 1. There are no more coupons of that kind in our stock.
     *                              2. The end date of this coupon has already passed
     */
    @Override
    public void purchaseCoupon(int couponId) throws UserErrorException, CouponStockException {
        Coupon coupon = couponsRepo.findById(couponId).orElseThrow(() -> new UserErrorException("* There is no coupon with that id. Purchase failed."));
        if (customerLoggedIn.getCoupons().contains(coupon)) {
            throw new UserErrorException("* This coupon was already purchased by you." +
                    " A customer can't have two copies of the same coupon. No purchase was done.");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponStockException("* Sorry, there are no more coupons of that" +
                    " kind in our stock. No purchase was done.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new CouponStockException("* The end-date of this coupon has already passed." +
                    " No purchase was done.");
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponsRepo.save(coupon);
        customerLoggedIn.getCoupons().add(coupon);
        customersRepo.save(customerLoggedIn);
    }

    @Override
    public void purchaseCouponForCreatingDB(int customerId, int couponId) throws UserErrorException, CouponStockException {
        Customer customer = customersRepo.findById(customerId).orElseThrow(() -> new UserErrorException("'CustomersImpl'", "'purchaseCoupon'", "There is no customer with the id: " + customerId));
        Coupon coupon = couponsRepo.findById(couponId).orElseThrow(() -> new UserErrorException("'CustomersImpl'", "'purchaseCoupon'", "There is no coupon with the id: " + couponId));
        if (customer.getCoupons().contains(coupon)) {
            throw new UserErrorException("'CustomersImpl'", "'purchaseCoupon'", "Coupon no. " + couponId + " was already purchased for this customer." +
                    " A customer can't have two copies of the same coupon. No purchase was done.");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponStockException("'CustomersImpl'", "'purchaseCoupon'", "Sorry, there are no more coupons of no. " + couponId +
                    " in our stock. No purchase was done.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new CouponStockException("'CustomersImpl'", "'purchaseCoupon'", "The end date of coupon no. " + couponId + " has already passed." +
                    " No purchase was done.");
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponsRepo.save(coupon);
        customer.getCoupons().add(coupon);
        customersRepo.save(customer);
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
        return customerLoggedIn.getCoupons();
    }

    /**
     * Gets from the data base all the coupons that were purchased by a specific customer
     * that belongs to a specific category, and puts them into a list.
     *
     * @param category - A Category enum.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCouponsByCategory(Category category) throws UserErrorException {
        return customerLoggedIn.getCoupons().stream().filter(coupon -> coupon.getCategory() == category).collect(Collectors.toList());
    }

    /**
     * Gets all the coupons of a specific customer, under or equal to a given price, from the data base,
     * and puts them into a list.
     *
     * @param customerId - The id of the customer.
     * @param maxPrice   - The price's upper limit.
     * @return - A list of Customer objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(int customerId, double maxPrice) throws UserErrorException {
        if (maxPrice < 0) {
            throw new UserErrorException("* The maximum price can't be below 0. Try again.");
        }
        return customerLoggedIn.getCoupons().stream().filter(coupon -> coupon.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    /**
     * Gets a customer from the database, and prints its details.
     *
     * @param customerId - The id of the customer.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public void showCustomerDetails(int customerId) throws UserErrorException {
        System.out.println(customerLoggedIn);
    }

    @Override
    public void purchaseCartCoupons(List<Coupon> coupons) throws UserErrorException, CouponStockException {
        for (Coupon coupon : coupons) {
            if (customerLoggedIn.getCoupons().contains(coupon)) {
                throw new UserErrorException("* Coupon number " + coupon.getCouponId() + " was already purchased by this customer. A customer can not have more then one of the same coupon.");
            }
            if (coupon.getAmount() == 0) {
                throw new CouponStockException("* Sorry, there are no more of coupon number " + coupon.getCouponId() + " in our stock. No purchase was done.");
            }
            if (coupon.getEndDate().before(new Date())) {
                throw new CouponStockException("* The end-date of coupon number number " + coupon.getCouponId() + " in the list has already passed. No purchase was done.");
            }
            coupon.setAmount(coupon.getAmount() - 1);
            couponsRepo.saveAndFlush(coupon);
            customerLoggedIn.getCoupons().add(coupon);
            customersRepo.saveAndFlush(customerLoggedIn);
        }
    }

    /**
     * Gets the logged-in Customer object
     *
     * @return - The 'customerLoggedIn' object.
     */
    public Customer getCustomerLoggedIn() {
        return customerLoggedIn;
    }

}
