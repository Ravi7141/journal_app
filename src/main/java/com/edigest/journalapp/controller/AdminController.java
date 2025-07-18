package com.edigest.journalapp.controller;

import com.edigest.journalapp.cache.AppCache;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserService userService;

    @GetMapping("getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getUsers();
        if(allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("createAdmin")
    public void createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
