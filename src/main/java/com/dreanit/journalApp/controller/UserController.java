package com.dreanit.journalApp.controller;


import com.dreanit.journalApp.entity.User;
import com.dreanit.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getEntries();
    }


    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("id/{id}")
    public User getUserById(@PathVariable ObjectId id) {
        return userService.getEntryById(id).orElse(null);
    }

    @DeleteMapping
    public boolean deleteUserByUserName(@PathVariable ObjectId id) {
//        userService.deleteByUserName(id);
        return true;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        User old = userService.findByUserName(user.getUserName());
        if(old!=null){
            old.setUserName(user.getUserName());
            old.setPassword(user.getPassword());
            userService.saveUser(old);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
