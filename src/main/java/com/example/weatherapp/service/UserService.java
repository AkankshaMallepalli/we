
package com.example.weatherapp.service;

import com.example.weatherapp.entity.CustomUser;

public interface UserService {
    CustomUser registerUser(CustomUser user);
    CustomUser loginUser(String username, String password);
    CustomUser getUserById(Long id);
}
