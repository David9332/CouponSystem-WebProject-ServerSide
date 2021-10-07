package com.example.SpringProject.Repositories;

import com.example.SpringProject.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompaniesRepo extends JpaRepository<Company, Integer> {
    /**
     * Gets one company from the DB by name or email.
     *
     * @param name  - The name of the company.
     * @param email - The email of the company.
     * @return - A Company object.
     */
    Company findByNameOrEmail(String name, String email);

    Company findByName(String name);

    Company findByEmail(String email);

    Company findByPassword(String password);

    Company findByNameAndPassword(String name, String password);

    /**
     * Gets one company from the DB by company id and name.
     *
     * @param companyId - The id of the company.
     * @param name      - The name of he company.
     * @return - A Company object.
     */
    Company findByCompanyIdAndName(int companyId, String name);

    /**
     * Gets one company from the DB by email and password.
     *
     * @param email    - The email of the company.
     * @param password - The password of the company.
     * @return - A Company object.
     */
    Company findByEmailAndPassword(String email, String password);

}
