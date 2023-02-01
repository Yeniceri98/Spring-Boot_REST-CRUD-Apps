package com.springboot.restcrudhibernateapp.dao;

import com.springboot.restcrudhibernateapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}

/*
    JpaRepository<Employee, Integer> kısmında "Employee" class'ını görür ve sonuna "s" ekleyerek /employees endpointinde tüm kullanıcıları döndürür. Diğer endpointler de yine önceki projelerdeki gibidir
    İstek atıldığı zaman veriler HATEOAS formatında gözükür

    NOT:
    Önceki projelerde Controller classında base path olarak "api" verdiğimiz için /api/employees veya /api/employees/1 gibi endpointler vardı
    Spring Data REST'te Controller classı olmadığı için böyle bir base path ataması olmadı ve /employees veya /employees/1 endpointiyle istek attık
    Base path kısmını application.properties kısmında değiştirebiliriz. Böylelikle endpointi custom hale getirebiliriz

    NOT:
    Önceki yöntemlerden farklı olarak Postman'de PUT isteğiyle güncelleme yaparken id'yi body kısmında değil URL kısmında vermeliyiz
*/