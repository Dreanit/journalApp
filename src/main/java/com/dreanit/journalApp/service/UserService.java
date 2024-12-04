package com.dreanit.journalApp.service;

import com.dreanit.journalApp.entity.User;
import com.dreanit.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository _userRepo;

    public void saveUser(User user) {
        _userRepo.save(user);
    }

    public List<User> getEntries() {
        return _userRepo.findAll();
    }

    public User findByUserName(String userName) {
        return _userRepo.findByUserName(userName);
    }
    public Optional<User> getEntryById(ObjectId id) {
        return _userRepo.findById(id);
    }

    public void deleteByUserName(String userName) {
        _userRepo.deleteByUserName(userName);
    }


}