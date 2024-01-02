package com.example.User_Project1.service;

import com.example.User_Project1.model.User;
import com.example.User_Project1.poll.PollService;
import com.example.User_Project1.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.example.User_Project1.ConstVariables.ConstVariables.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public void createUser(User user) throws JsonProcessingException {
        userRepository.createUser(user);
    }
    @Override
    public User readUser(Integer id) {
        return userRepository.readUser(id);
    }
    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
    @Override
    public void deleteUser(Integer id, String delToken) {
        if (!Objects.equals(delToken, DELETE_TOKEN)) {
            logger.error(String.format("INCORRECT PASSWORD! CANNOT DELETE USER WITH ID:\"%s\"",id));
            return;
        }
        User userToRemove = userRepository.readUser(id);
        if (userToRemove == null) {
            logger.warn(String.format("USER WITH ID:\"%s\" NOT EXIST. IMPOSSIBLE TO DELETE!",id));
            return;
        }
        if (userToRemove.getRegistered()) {
            logger.info(String.format("USER WITH ID:\"%s\" IS ALREADY REGISTERED. SENDING AN API TO POLL TO DELETE ANSWERS.",id));
            pollService.deleteAnswersByUserId(id, DELETE_TOKEN_TO_POLL_SERVICE);
        }
        userRepository.deleteUser(id);
        logger.info(String.format("USER ID:\"%s\" HAS DELETED!",id));
    }


    @Override
    public void handleRegistration(Integer id, String regToken) throws ParseException {
        User userToHandle = userRepository.readUser(id);
        if (userToHandle == null) {
            logger.error(String.format("USER WITH ID:\"%s\" IS NOT EXIST!",id));
            return;
        }
        if (!checkAgePermission(userToHandle.getDateBirth())) {
            logger.warn(String.format("THE USER ID:\"%s\" IS UNDER THE AUTHORIZED AGE! CAN'T BE REGISTERED!!",id));
            return;
        }
        if (Objects.equals(regToken, REGISTER_TOKEN)) {
            logger.info(String.format("REGISTER TOKEN FOR USER ID:\"%s\" CORRECT. WILL REGISTER :-)",id));
            userRepository.handleRegistration(id, true);
        } else if (Objects.equals(regToken, UNREGISTER_TOKEN)) {
            logger.warn(String.format("UNREGISTER TOKEN FOR USER ID:\"%s\" CORRECT. WILL REMOVE!",id));
            userRepository.handleRegistration(id, false);
            pollService.deleteAnswersByUserId(id, DELETE_TOKEN_TO_POLL_SERVICE);
        } else {
            logger.error("TOKEN IS INCORRECT :-(");
        }
    }


    @Override
    public Boolean checkConfirmed(Integer id) {
        User userToConfirm = userRepository.readUser(id);

        if (!(userToConfirm == null)) {
            if (userToConfirm.getRegistered()) {
                logger.info(String.format("THE USER WITH ID:\"%s\" IS CONFIRMED :-)",id));
                return true;
            } else {
                logger.warn(String.format("THE USER WITH ID:\"%s\" IS NOT CONFIRMED :-(",id));
                return false;
            }
        } else {
            return false;
        }
    }
    public static boolean checkAgePermission(Date givenDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date givenDateInParse = sdf.parse(givenDate.toString());
        Date currentDate = new Date();

        long millisecondsDifference = currentDate.getTime() - givenDateInParse.getTime();
        double yearsDifference = millisecondsDifference / (365.25 * 24 * 60 * 60 * 1000);

        return yearsDifference > AGE_LIMIT || !TO_OPERATE_AGE_LIMIT;
    }
}
