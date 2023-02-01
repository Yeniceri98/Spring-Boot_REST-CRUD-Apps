package com.springboot.restcrudhibernateapp.dao;

import com.springboot.restcrudhibernateapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "members")       // NOT: Default olarak /employees olan endpointi /members şeklinde değiştirdik
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}

/*
    NOT:
    Spring Data REST default olarak JpaRepository<Employee, Integer> kısmında "Employee" class'ını görür ve sonuna "s" ekleyerek /employees endpointinde tüm kullanıcıları döndürür. Diğer endpointler de yine önceki projelerdeki gibidir
    @RepositoryRestResource anotasyonu ile bu default path'i değiştirebiliriz
    İstek atıldığı zaman veriler HATEOAS formatında gözükür

    NOT:
    Önceki projelerde Controller classında base path olarak "api" verdiğimiz için /api/employees veya /api/employees/1 gibi endpointler vardı
    Spring Data REST'te Controller classı olmadığı için böyle bir base path ataması olmadı ve /employees veya /employees/1 endpointiyle istek attık
    Base path kısmını application.properties kısmında değiştirebiliriz. Böylelikle endpointi custom hale getirebiliriz

    NOT:
    Önceki yöntemlerden farklı olarak Postman'de PUT isteğiyle güncelleme yaparken id'yi body kısmında değil URL kısmında vermeliyiz


    _____ PAGINATION _____
    By default, Spring Data REST will return the first 20 elements. We can change this in application.properties file
    You can navigate to the different pages of data using query param:
    http://localhost:8080/employees?page=0
    http://localhost:8080/employees?page=1


    _____ SORTING _____
    In our example, we have firstName, lastName and email

    Sort by last name (Default is ascending):
    http://localhost:8080/employees?sort=lastName

    Sort by first name, descending:
    http://localhost:8080/employees?sort=firstName, desc
*/