package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.AdminImpl;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Service.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class TestCompany implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            CompaniesImpl companiesImpl = (CompaniesImpl) loginManager.login("amazon@gmail.com", "5mq775mq7hg7", ClientType.COMPANY);

            Coupon coupon1 = Coupon.builder().companyId(1).category(Category.ELECTRICITY).title("Ace").description("BOSCH washing machine, model WAN28160BY for only 1,000 NIS").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(1000.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/electricDevices.jpg").build();
            Coupon coupon2 = Coupon.builder().companyId(1).category(Category.FOOD).title("Osher Ad").description("1 kg fresh veal sirloin - 79.90 NIS").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-03-29")).amount(50).price(79.90).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/shoppingCart.jpg").build();
            Coupon coupon3 = Coupon.builder().companyId(1).category(Category.RESTAURANT).title("Caffe caffe").description("Israeli breakfast for 57 NIS").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-03-29")).amount(50).price(57.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/restaurant.jpg").build();
            Coupon coupon4 = Coupon.builder().companyId(1).category(Category.VACATION).title("Club hotel").description("Double or Twin Room – Breakfast included, FREE cancellation • No prepayment needed").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-03-29")).amount(50).price(1500.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/Vacation.jpg").build();
            Coupon coupon5 = Coupon.builder().companyId(2).category(Category.ELECTRICITY).title("Electric Shop").description("75″ Class TU8000 Crystal UHD 4K Smart TV (2020)").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(2000.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/electricDevices.jpg").build();
            Coupon coupon6 = Coupon.builder().companyId(2).category(Category.FOOD).title("SoGood night store").description("Buy at 200 and get products at 300").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(200.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/shoppingCart.jpg").build();
            Coupon coupon7 = Coupon.builder().companyId(2).category(Category.RESTAURANT).title("Pizza boom").description("1 plus 1 on buying a family pizza").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(50.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/restaurant.jpg").build();
            Coupon coupon8 = Coupon.builder().companyId(2).category(Category.VACATION).title("Royal Beach").description("2 rooms Double bed with balcony For two nights").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(1000.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/Vacation.jpg").build();
            Coupon coupon9 = Coupon.builder().companyId(3).category(Category.ELECTRICITY).title("Big Electric").description("LG 70 Class 4K UHD 2160P Smart TV with HDR - 70UN7070PUA 2020 Model").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(2500.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/electricDevices.jpg").build();
            Coupon coupon10 = Coupon.builder().companyId(3).category(Category.FOOD).title("Home store").description("Olive oil for 10 NIS").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(10.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/shoppingCart.jpg").build();
            Coupon coupon11 = Coupon.builder().companyId(3).category(Category.RESTAURANT).title("Meat meat").description("The house sandwich includes drink and dessert").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(49.90).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/restaurant.jpg").build();
            Coupon coupon12 = Coupon.builder().companyId(3).category(Category.VACATION).title("Jerusalem tours").description("A tour in Jerusalem in the holy places includes lunch").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(100.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/Vacation.jpg").build();
            Coupon coupon13 = Coupon.builder().companyId(4).category(Category.ELECTRICITY).title("Electric city").description("S3821w-C0 38-Inch Sound Bar with Bluetooth Wireless Sub").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(500.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/electricDevices.jpg").build();
            Coupon coupon14 = Coupon.builder().companyId(4).category(Category.FOOD).title("Best big").description("Buy at 100 and get products at 200").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(0).price(100.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/shoppingCart.jpg").build();
            Coupon coupon15 = Coupon.builder().companyId(4).category(Category.RESTAURANT).title("Italiano").description("A meal for two that includes a main course and dessert").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(99.90).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/restaurant.jpg").build();
            Coupon coupon16 = Coupon.builder().companyId(4).category(Category.VACATION).title("Gil hotel").description("2 nights per couple including breakfast").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-04-12")).amount(50).price(500.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/Vacation.jpg").build();
            Coupon coupon17 = Coupon.builder().companyId(5).category(Category.ELECTRICITY).title("David's Electrics").description("SAMSUNG 70 Class LED 4K (2160P) LED Smart TV QN70Q60 2021").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(1500.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/electricDevices.jpg").build();
            Coupon coupon18 = Coupon.builder().companyId(5).category(Category.FOOD).title("Super pharma").description("Chicken for 5 NIS").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(5.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/shoppingCart.jpg").build();
            Coupon coupon19 = Coupon.builder().companyId(5).category(Category.RESTAURANT).title("Burger burger").description("400g hamburger with fries and a drink").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(java.sql.Date.valueOf("2028-08-08")).amount(50).price(49.90).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/restaurant.jpg").build();
            Coupon coupon20 = Coupon.builder().companyId(5).category(Category.VACATION).title("Dolphin Reef").description("A tour of the Dolphin Reef including all the attractions").startDate(java.sql.Date.valueOf("2020-04-04")).endDate(Date.valueOf("2028-08-08")).amount(50).price(53.00).image("https://backendlessappcontent.com/F161E0F9-F7B0-3E9D-FF9E-0815CA080100/9AF04F41-0BAF-4825-B091-EA7E6355C2B8/files/myPictures/Vacation.jpg").build();

            List<Coupon> coupons = Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7, coupon8, coupon9, coupon10, coupon11, coupon12, coupon13, coupon14, coupon15, coupon16, coupon17, coupon18, coupon19, coupon20);
            companiesImpl.addCouponsListToDB(coupons);

            List<Coupon> coupons1 = Arrays.asList(coupon1, coupon2, coupon3, coupon4);
            List<Coupon> coupons2 = Arrays.asList(coupon5, coupon6, coupon7, coupon8);
            List<Coupon> coupons3 = Arrays.asList(coupon9, coupon10, coupon11, coupon12);
            List<Coupon> coupons4 = Arrays.asList(coupon13, coupon14, coupon15, coupon16);
            List<Coupon> coupons5 = Arrays.asList(coupon17, coupon18, coupon19, coupon20);

            companiesImpl.addCouponsListToCompany(1, coupons1);
            companiesImpl.addCouponsListToCompany(2, coupons2);
            companiesImpl.addCouponsListToCompany(3, coupons3);
            companiesImpl.addCouponsListToCompany(4, coupons4);
            companiesImpl.addCouponsListToCompany(5, coupons5);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
