package com.RockStudio.Smoothly.repository;

import com.RockStudio.Smoothly.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByName(String name);

    public List<User> findByEmail(String email);
}
