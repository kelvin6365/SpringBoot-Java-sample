package com.RockStudio.Smoothly.repository;

import com.RockStudio.Smoothly.model.Address;
import io.leangen.graphql.execution.relay.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {
}
