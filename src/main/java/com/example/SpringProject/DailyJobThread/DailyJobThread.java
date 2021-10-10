package com.example.SpringProject.DailyJobThread;

import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.CompaniesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DailyJobThread {

    /**
     * Runs a thread that deletes expired coupons once a day at 02:00.
     */
    private final CompaniesImpl companiesImpl;
    @Async
    @Scheduled(cron = "0 2 0 * * ?")
    public void deleteCoupons() {
        Date date = new Date();
        companiesImpl.deleteCouponByDate(date);
    }

}
