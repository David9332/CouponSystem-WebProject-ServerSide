package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Repositories.CustomersRepo;
import com.example.SpringProject.Service.*;
import com.example.SpringProject.Utills.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class TestAdmin implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println(Art.sperator);
            System.out.println();
            System.out.println(Art.INIT_DATABASE);
            AdminImpl adminImpl = (AdminImpl) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

            Company company1 = Company.builder().name("Microsoft").email("microsoft@gmail.com").password("1b341b34gg").build();
            Company company2 = Company.builder().name("Apple").email("apple@gmail.com").password("12e412e4hg").build();
            Company company3 = Company.builder().name("Amazon").email("amazon@gmail.com").password("5mq775mq7hg7").build();
            Company company4 = Company.builder().name("Samsung").email("samsung@gmail.com").password("w9f6w9f6df").build();
            Company company5 = Company.builder().name("Intel").email("intel@gmail.com").password("jl5ijlsg5i").build();

            List<Company> companies = Arrays.asList(company1, company2, company3, company4, company5);
            adminImpl.addCompaniesListToDB(companies);

            Customer customer1 = Customer.builder().firstName("David").lastName("Birger").email("david@gmail.com").password("qafdswqasaw").build();
            Customer customer2 = Customer.builder().firstName("Tom").lastName("Hacarmeli").email("tom@gmail.com").password("tyydfgystyygy").build();
            Customer customer3 = Customer.builder().firstName("Yossi").lastName("Cohen").email("yossi@gmail.com").password("fgdindfdfgdf").build();
            Customer customer4 = Customer.builder().firstName("Avi").lastName("Levy").email("avi@gmail.com").password("cvvkcvcddvk").build();
            Customer customer5 = Customer.builder().firstName("Israel").lastName("Israeli").email("israel@gmail.com").password("344dgf73447").build();
            Customer customer6 = Customer.builder().firstName("Ron").lastName("Alon").email("ron@gmail.com").password("k43yyddk43yy").build();
            Customer customer7 = Customer.builder().firstName("Benjamin").lastName("Netanyahu").email("benjamin@gmail.com").password("8g57dgb8g57").build();
            Customer customer8 = Customer.builder().firstName("Sara").lastName("Netanyahu").email("sara@gmail.com").password("55h34f55hd34").build();
            Customer customer9 = Customer.builder().firstName("Benny").lastName("Gantz").email("benny@gmail.com").password("748f74f8df").build();
            Customer customer10 = Customer.builder().firstName("Naftali").lastName("Bennet").email("naftali@gmail.com").password("j8f7dj6j87j6").build();

            List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4, customer5, customer6, customer7, customer8, customer9, customer10);
            adminImpl.addCustomersListToDB(customers);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}

