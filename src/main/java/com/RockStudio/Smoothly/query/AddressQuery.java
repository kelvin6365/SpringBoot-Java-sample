package com.RockStudio.Smoothly.query;

import com.RockStudio.Smoothly.model.Address;
import com.RockStudio.Smoothly.service.AddressService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.relay.Page;
import io.leangen.graphql.execution.relay.generic.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<Address> allAddress(
            @GraphQLNonNull @GraphQLArgument(name = "first") int first,
            @GraphQLNonNull @GraphQLArgument(name = "after") String after
    ){
        //final Optional<User> searchResult = userService.getById(id);
        int offset = Integer.parseInt(after);
        System.out.println( addressService.getAll().size());
        List<Address> addresses = addressService.getAll().stream().limit(first).collect(Collectors.toList());
        System.out.println( addresses);
        return PageFactory.createOffsetBasedPage(addresses, addresses.size(), offset);
    }
}
