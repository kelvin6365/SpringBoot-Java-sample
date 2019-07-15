package com.RockStudio.Smoothly.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;


@Document
public class User {
    @Id
    String id;
    String name;
    String email;
    String password;

    public  User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean validate() {
        if (this.name == null) {
            return false;
        }
        if (this.password == null) {
            return false;
        }
        if (this.email == null) {
            return false;
        }
        return true;
    }

    public String errorField() {
        String error="";
        if(this.name == null||this.name.length() == 0||this.password == null||this.password.length() == 0||this.email == null||this.email.length() == 0){
            if (this.name == null||this.name.length()==0) {
                if(this.name.length()==0)
                {error= error+ "name value ";}
                else
                {error= error+"name ";}
            }
            if (this.password == null||this.password.length()==0) {
                if(this.password.length()==0)
                {error= error+ "password value ";}
                else
                { error= error+ "password ";}
            }
            if (this.email == null||this.email.length()==0) {
                if(this.email.length()==0)
                {error= error+ "emaill value ";}
                else
                {error= error+ "emaill ";}
            }
            return error;
        }

        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @GraphQLQuery(name = "name", description = "A person's name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emai='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
