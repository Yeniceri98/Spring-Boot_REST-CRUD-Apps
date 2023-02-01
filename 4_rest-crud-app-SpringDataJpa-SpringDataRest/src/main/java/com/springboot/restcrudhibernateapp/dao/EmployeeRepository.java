package com.springboot.restcrudhibernateapp.dao;

import com.springboot.restcrudhibernateapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {      // <Entity type, Primary key>

    // That's it... No more code needed. We get all CRUD methods like findAll(), findById(), save() etc
}
