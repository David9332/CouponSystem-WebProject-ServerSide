package com.example.SpringProject.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClientType {
    ADMINISTRATOR("admin@admin.com", "admin"),
    COMPANY,
    CUSTOMER;

    private String email;
    private String password;
}
