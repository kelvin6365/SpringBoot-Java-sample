package com.RockStudio.Smoothly.controller;

import com.RockStudio.Smoothly.model.User;
import com.RockStudio.Smoothly.repository.UserRepository;
import com.RockStudio.Smoothly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/create")
    public String create(@RequestParam String name,@RequestParam String password,@RequestParam String email){
        User u = userService.create(name,password,email);
        return u.toString();
    }

    @RequestMapping("/get")
    public  User getUser(@RequestParam String name){
        return userService.getByName(name);
    }

    @RequestMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping("/update")
    public  String update(@RequestParam String name,@RequestParam String password,@RequestParam String email){
        User u = userService.update(name,email,password);
        return  u.toString();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String name){
        userService.delete(name);
        return  "Delete "+name;
    }
}
