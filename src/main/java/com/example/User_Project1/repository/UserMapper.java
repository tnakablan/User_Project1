package com.example.User_Project1.repository;

import com.example.User_Project1.model.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getDate("date_birth"),
                rs.getInt("age"),
                rs.getString("address"),
                rs.getDate("joined_date"),
                rs.getBoolean("is_registered")

        );
    }
}
