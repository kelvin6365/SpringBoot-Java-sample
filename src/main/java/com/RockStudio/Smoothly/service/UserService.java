package com.RockStudio.Smoothly.service;

import com.RockStudio.Smoothly.model.User;
import com.RockStudio.Smoothly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //create
    public User create(String name,String email, String password){
        return  userRepository.save(new User(name, password,email));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByName(String name){
        return userRepository.findByName(name);
    }

    public  User update(String name, String email, String password){
        User u = userRepository.findByName(name);
        u.setEmail(email);
        u.setPassword(password);
        return userRepository.save(u);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void delete(String name){
        User u = userRepository.findByName(name);
        userRepository.delete(u);
    }
}
