package com.springboot.restcrudhibernateapp.service;

import com.springboot.restcrudhibernateapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);
}
