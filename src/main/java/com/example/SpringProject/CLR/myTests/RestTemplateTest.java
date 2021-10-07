package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@Order(4)
public class RestTemplateTest implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
/*
          String uri = "http://localhost:8080/springProject/getOneCustomer/10";
         Customer resultCustomer = restTemplate.getForObject(uri, Customer.class);
        System.out.println(resultCustomer);


        System.out.println();
        ResponseEntity<Customer[]> response = restTemplate.getForEntity("http://localhost:8080/springProject/getAllCustomers", Customer[].class);
        List<Customer> customers = Arrays.asList(response.getBody());
        for (Customer item : response.getBody()) {
            System.out.println(item);


            Customer chen = Customer
                    .builder()
                    .firstName("Chen")
                    .lastName("Amos")
                    .email("chen@chen.com")
                    .password("xxxxx")
                    .build();

            String uri_post = "http://localhost:8080/springProject/addCustomer";
            ResponseEntity<String> res = restTemplate.postForEntity(uri_post, chen, String.class);
            System.out.println(res);
            System.out.println("all is ok??? " + (res.getStatusCodeValue() == 201));


        Customer chen = Customer
                .builder()
                .customerId(7)
                .firstName("yyy")
                .lastName("yyy")
                .email("yyy@yyy.com")
                .password("yyy")
                .build();

        String uri_put = "http://localhost:8080/springProject/updateCustomer";
        restTemplate.put(uri_put, chen);



        String uri_delete = "http://localhost:8080/springProject/deleteCustomer/3";
        restTemplate.delete(uri_delete);

*/
    }
}

