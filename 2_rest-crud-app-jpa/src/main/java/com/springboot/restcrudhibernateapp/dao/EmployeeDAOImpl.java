package com.springboot.restcrudhibernateapp.dao;

import com.springboot.restcrudhibernateapp.entity.Employee;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

        // Get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // Create a query
        Query<Employee> theQuery = currentSession.createQuery("FROM Employee", Employee.class);     // Using natice Hibernate API

        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // Return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);

        // Get the employee
        Employee theEmployee = currentSession.get(Employee.class, theId);

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        Session currentSession = entityManager.unwrap(Session.class);

        // Save the employee
        currentSession.merge(theEmployee);      // NOT: saveOrUpdate() metodu deprecate olmuş. Onun yerine merge() ve persist() metodlarının kullanılması öneriliyor. merge() yapınca add ve update user düzgün çalıştı
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);

        // Get the employee
        Employee theEmployee = currentSession.get(Employee.class, theId);

        // Delete the employee
        currentSession.remove(theEmployee);       // NOT: delete() metodu deprecate olmuş. Onun yerine remove() kullabılabilir
    }
}

