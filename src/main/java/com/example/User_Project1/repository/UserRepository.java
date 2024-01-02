package com.example.User_Project1.repository;

import com.example.User_Project1.model.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface UserRepository {
    public void createUser(User user);
    public User readUser(Integer id);
    public void updateUser(User user) throws EmptyResultDataAccessException;
    public void deleteUser(Integer id);
    public void handleRegistration(Integer id, Boolean isRegistered);
    public List<User> readAllUsers();
}
