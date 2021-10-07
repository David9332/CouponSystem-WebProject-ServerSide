package com.example.SpringProject.Exceptions;

public class CouponStockException extends Exception{
    /**
     * A constructor for Coupons Stock Exceptions: when the user tries to order
     * a coupon that ran-out, or end-time had passed.
     *
     * @param className  - The class in which the exception occurred.
     * @param methodName - The method in which exception occurred.
     * @param message    - The possible explanation for the exception.
     */
    public CouponStockException(String className, String methodName, String message) {
        System.out.println("\nCoupons stock error at: \nCLASS: " + className + ", METHOD: " + methodName + "\n" + message+"\n");
    }

    public CouponStockException(String message) {
        super(message);
    }
}
