package com.springboot.restcrudhibernateapp.dao;

import com.springboot.restcrudhibernateapp.entity.Employee;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // Define field for EntityManager
    private EntityManager entityManager;

    // Constructor Injection (We can use other injections as well)
    // NOT: EntityManager automatically created by Spring Boot. We can simply inject it here into our application
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Create the query
        Query theQuery = entityManager.createQuery("FROM Employee");

        // Execute the query and get the result list
        List<Employee> employees = theQuery.getResultList();

        // Return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // Get the employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // Return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {

        // Save or Update the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // Update with id from db to be able to generated id for save/insert
        theEmployee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int theId) {

        // Delete with primary key
        Query theQuery = entityManager.createQuery("DELETE FROM Employee WHERE id=:employeeId");

        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();

    }
}

