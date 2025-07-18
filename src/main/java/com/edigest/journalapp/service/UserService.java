package com.edigest.journalapp.service;

import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
            // not using this line because annotation is already present in the class

    // Save the user to the database without encryption
    public void saveEntry(User user) {
        userRepo.save(user);
    }

    // Save the user to the database with encryption
    public void savenewEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("user"));
        userRepo.save(user);
    }


    public List<User> getUsers() {
        return userRepo.findAll();
    }
    public Optional<User> getUser(ObjectId id) {
        return userRepo.findById(id);
    }
    public void deleteUser(ObjectId id) {
        userRepo.deleteById(id);
    }
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("user", "ADMIN    "));
        userRepo.save(user);
    }
}
