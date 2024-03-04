package com.example.weatherapp.repository;

import com.example.weatherapp.entity.CustomUser; // Changed import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> { // Changed User to CustomUser
    CustomUser findByUsername(String username); // Changed User to CustomUser
}
