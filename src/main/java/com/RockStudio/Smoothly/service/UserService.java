package com.RockStudio.Smoothly.service;

import com.RockStudio.Smoothly.model.User;
import com.RockStudio.Smoothly.repository.UserRepository;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import graphql.GraphQLException;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //create
    public User create(String name,String email, String password){
        User user = new User(name,email,password);
        if(user.validate()&&name.length()!=0&&email.length()!=0&&password.length()!=0){
            userRepository.save(user);
            return  user;
        }
        else
        {
            try {
                String errorFeild = user.errorField();
                throw new Exception("User "+errorFeild+"missing!");
            } catch (Exception e) {
                throw new GraphQLException(e.getMessage());
            }
        }

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public Optional<User> getById(String id){
        return userRepository.findById(id);
    }

    public User getByName(String name){
        return userRepository.findByName(name);
    }

    public  User update(String id,String name, String email, String password){
        User u =  userRepository.findById(id).get();
        u.setName(name);
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
