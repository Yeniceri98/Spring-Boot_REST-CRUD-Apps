package com.springboot.restcrudhibernateapp.dao;

import com.springboot.restcrudhibernateapp.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findAll();        // NOT: public eklemeye gerek yoktur. Alttakilerde koymadık   --->  https://stackoverflow.com/questions/17011374/are-public-and-public-final-redundant-for-interface-fields

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);
}
