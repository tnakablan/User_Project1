package com.example.User_Project1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class User {
    @JsonProperty(value = "id")
    private Integer userId;
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String LastName;
    private String email;
    private Date dateBirth;
    private int age;

    @JsonProperty(value = "address")
    private String userAddress;
    @JsonProperty(value = "joined_date")
    private Date joinedDate;
    private Boolean isRegistered;


    public User(Integer userId, String firstName, String lastName, String email, Date dateBirth, int age, String userAddress, Date joinedDate, Boolean isRegistered) {
        this.userId = userId;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.age = age;
        this.userAddress = userAddress;
        this.joinedDate = joinedDate;
        this.isRegistered = isRegistered;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }
}
