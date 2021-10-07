package com.example.SpringProject.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Singular
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
