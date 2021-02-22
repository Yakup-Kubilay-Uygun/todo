package com.example.felece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication()
@ComponentScan("com.example.felece.Controller")
@ComponentScan("com.example.felece.Service")
public class FeleceApplication {

	//email : admin@gmail.com
	//şifre : admin1
	public static void main(String[] args) {
		SpringApplication.run(FeleceApplication.class, args);
	}

}
