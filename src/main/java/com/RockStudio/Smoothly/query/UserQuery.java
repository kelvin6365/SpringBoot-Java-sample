package com.RockStudio.Smoothly.query;

import com.RockStudio.Smoothly.model.User;
import com.RockStudio.Smoothly.service.UserService;
import graphql.GraphQLError;
import io.leangen.graphql.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.io.Console;
import java.util.List;
import java.util.Optional;

@Component
public class UserQuery {
    @Autowired
    private UserService userService;

    /**
     * Save new Vendor.
     *
     * Invoke with:
     * mutation createUser{createUser(name: "vendor0",email:"",password:"",id:""){User}}
     *
     * @param user
     * @return
     */
    @GraphQLMutation(name = "createUser")
    public User createUser(
            @GraphQLArgument(name = "email")  @Valid String email ,
            @GraphQLArgument(name = "name")   @Valid String name,
            @GraphQLArgument(name = "password")   @Valid String password
    ){
        User u = userService.create(name,email,password);
        System.out.println(u);
        return u;
    }
    /**
     * Retrieve saved User by id.
     * Invoke after you get an id from the createUser mutation.
     *
     * Invoke with
     * {userById(id:0){id,name,email,password}}
     *
     * @param id
     * @return
     */
    @GraphQLQuery(name = "userById")
    public User getUser(@GraphQLArgument(name = "id") String id){
        //final Optional<User> searchResult = userService.getById(id);
        System.out.println( userService.getById(id).get());
        return userService.getById(id).get();
    }

    @GraphQLQuery(name = "allUsers")
    public List<User> allUsers(){
        //final Optional<User> searchResult = userService.getById(id);
        System.out.println( userService.getAll());
        return userService.getAll();
    }
}
