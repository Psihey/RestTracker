package com.restTracker.restTracker.service;


import com.restTracker.restTracker.model.jwt.JwtRequest;
import com.restTracker.restTracker.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public JwtRequest findUserByName(String username){
       return userRepository.findByUsername(username);
    }

    public void saveNewUser(JwtRequest user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }
}
