package com.RockStudio.Smoothly.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
    @Id
    String id;
    String name;

    public Address( String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @GraphQLQuery(name = "name", description = "An address's name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
