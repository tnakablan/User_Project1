package com.example.User_Project1.service;

import com.example.User_Project1.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;

public interface UserService {
    public void createUser(User user) throws JsonProcessingException;
    public User readUser(Integer id);
    public void updateUser(User user);

    public void deleteUser(Integer id, String delToken);
    public void handleRegistration(Integer id, String regToken) throws ParseException;



    public Boolean checkConfirmed(Integer id);
}
