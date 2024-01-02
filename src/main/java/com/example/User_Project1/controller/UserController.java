package com.example.User_Project1.controller;

import com.example.User_Project1.model.User;
import com.example.User_Project1.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public void createUser(@RequestBody User user) throws JsonProcessingException {
        userService.createUser(user);
    }
    @GetMapping("/read/{id}")
    public User readUser(@PathVariable Integer id) {
        return userService.readUser(id);
    }
    @PutMapping(value = "/update")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }


    @GetMapping(value = "/confirm/{id}")
    public Boolean checkConfirmed(@PathVariable(value = "id") Integer id) {
        return userService.checkConfirmed(id);
    }



}
