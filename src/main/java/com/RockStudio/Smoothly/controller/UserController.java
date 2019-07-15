package com.RockStudio.Smoothly.controller;

import com.RockStudio.Smoothly.model.User;
import com.RockStudio.Smoothly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getUser(@RequestParam String id){
        return userService.getById(id);
    }

    @RequestMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping("/update")
    public  String update(@RequestParam String id,@RequestParam String name,@RequestParam String password,@RequestParam String email){
        User u = userService.update(id,name,email,password);
        return  u.toString();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String name){
        userService.delete(name);
        return  "Delete "+name;
    }
}
