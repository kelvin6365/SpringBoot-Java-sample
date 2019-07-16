package com.RockStudio.Smoothly.query;

import com.RockStudio.Smoothly.model.Address;
import com.RockStudio.Smoothly.service.AddressService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class AddressQuery {
    @Autowired
    private AddressService addressService;

    @GraphQLMutation(name = "createAddress")
    public Address createAddress(
            @GraphQLArgument(name = "name")   @Valid String name
    ){
        Address a = addressService.create(name);
        System.out.println(a);
        return a;
    }

    @GraphQLQuery(name = "allAddress")
    public List<Address> allAddress(){
        //final Optional<User> searchResult = userService.getById(id);
        System.out.println( addressService.getAll());
        return addressService.getAll();
    }
}
