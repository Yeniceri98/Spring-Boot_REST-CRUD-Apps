package com.springboot.restcrudhibernateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration	// NOT: @SpringBootApplication anotasyonu bunu da kapsadığı için redundant hatası veriyor fakat bunu eklemeyince EmployeeDAOHibernateImpl classında "entityManager" autowire edilince hata veriyor  --->  https://stackoverflow.com/questions/26889970/intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository
public class RestCrudHibernateAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCrudHibernateAppApplication.class, args);
	}

}
