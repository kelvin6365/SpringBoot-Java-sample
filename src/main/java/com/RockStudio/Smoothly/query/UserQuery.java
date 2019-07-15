package com.RockStudio.Smoothly.query;

import com.RockStudio.Smoothly.model.User;
import com.RockStudio.Smoothly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Component
public class UserQuery {
    @Autowired
    private UserService userService;
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
