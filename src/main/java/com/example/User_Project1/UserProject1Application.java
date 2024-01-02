package com.example.User_Project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UserProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserProject1Application.class, args);
	}

}
