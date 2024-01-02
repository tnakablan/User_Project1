package com.example.User_Project1.repository;

import com.example.User_Project1.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.slf4j.Logger;


@Repository
public class UserRepositoryImpl implements UserRepository{
    private static final String USER_TABLE_NAME = "userr";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " " + "(first_name, last_name, email, date_birth, address) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateBirth(),
                user.getUserAddress()
        );
    }
    @Override
    public User readUser(Integer id) {
        String sql = "SELECT * FROM  " + USER_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            logger.error(String.format("READING: USER WITH ID:\"%s\" IS NOT EXIST!",id));
            return null;
        }
    }
    @Override
    public void updateUser(User user) throws EmptyResultDataAccessException{
        String sql = "UPDATE " + USER_TABLE_NAME + " SET first_name=?, last_name=?, email=?, date_birth=?, address=? WHERE id=?";
        try {
            jdbcTemplate.update(
                    sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getDateBirth(),
                    user.getUserAddress(),
                    user.getUserId()
            );
        } catch (EmptyResultDataAccessException e) {
            logger.error(String.format("\"%s\". UPDATING: USER WITH ID:\"%s\" IS NOT EXIST. IMPOSSIBLE TO UPDATE :-(",e,user.getUserId()));
        }
    }
    @Override
    public void deleteUser(Integer id) {
        String sql = "DELETE FROM " + USER_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public void handleRegistration(Integer id, Boolean isRegistered) {
        String sql = "UPDATE " + USER_TABLE_NAME + " SET is_registered=? WHERE id=?";
        jdbcTemplate.update(sql, isRegistered, id);
    }

    @Override
    public List<User> readAllUsers() {
        String sql = "SELECT * FROM " + USER_TABLE_NAME ;
        try {
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("READING ALL: USER LIST IS EMPTY!");
            return null;
        }
    }
}
