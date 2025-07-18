package com.edigest.journalapp.controller;


import com.edigest.journalapp.api.response.WheatherResponse;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.UserRepo;
import com.edigest.journalapp.service.UserService;
import com.edigest.journalapp.service.WheatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WheatherService wheatherService;



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User existingUser = userService.findByUsername(username);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userService.savenewEntry(existingUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WheatherResponse wheatherResponse = wheatherService.getWeather("Delhi");
        String greeting  = "";
        if(wheatherResponse != null) {
            greeting = " Weather feels like " + wheatherResponse.getCurrent().getFeelslike() + "°C";
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + greeting ,HttpStatus.OK);
    }
}
