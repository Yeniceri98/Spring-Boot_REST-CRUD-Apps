package com.springboot.restcrudhibernateapp.service;

import com.springboot.restcrudhibernateapp.dao.EmployeeDAO;
import com.springboot.restcrudhibernateapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    // Constuctor Injection (In order to use EmployeeDAO's methods)
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {
        employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }
}

/*
    Application Architecture:
    Employee REST Controller  <----->  Employee Service  <----->  Employee DAO - Hibernate  <----->  Database

    @Transactional anotasyonunu DAO'dan alıp burada tanımladık. Best practice olarak Service layer'ında transaction'ın açılıp kapanması önerilir. Her metod için eklenmelidir
    Metodlarda DAO'ya yönlendirme yapıyoruz
*/
