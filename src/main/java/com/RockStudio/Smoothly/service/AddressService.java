package com.RockStudio.Smoothly.service;

import com.RockStudio.Smoothly.model.Address;
import com.RockStudio.Smoothly.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address create(String name) {
        Address address = new Address(name);
        addressRepository.save(address);
        return address;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
