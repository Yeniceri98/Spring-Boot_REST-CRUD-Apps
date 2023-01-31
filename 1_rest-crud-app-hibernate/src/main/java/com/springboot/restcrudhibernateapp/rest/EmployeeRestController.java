package com.springboot.restcrudhibernateapp.rest;

import com.springboot.restcrudhibernateapp.dao.EmployeeDAO;
import com.springboot.restcrudhibernateapp.entity.Employee;
import com.springboot.restcrudhibernateapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    /*
        private EmployeeDAO employeeDAO;

        @Autowired
        public EmployeeRestController(EmployeeDAO employeeDAO) {
            this.employeeDAO = employeeDAO;
        }

        @GetMapping("/employees")
        public List<Employee> findAll() {
            return employeeDAO.findAll();
        }
    */

    // NOT: Service Layer'ını ekledikten sonra kodu aşağıdaki şekilde refactor ettik

    private EmployeeService employeeService;

    // Constructor Injection
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")               // Endpoint: /api/employees
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getSingleEmployee(@PathVariable int employeeId) {       // NOT: employeeId, @GetMapping'in endpointinde verdiğimizle aynı olmalıdır
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee is not found with the id of " + employeeId);
        }

        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {        // NOT: Employee verileri JSON formatında geldiği için @RequestBody anotasyonunu ekledikn
        employeeService.save(theEmployee);

        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);

        return theEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("The employee with the id of " + employeeId + " is not found!!!");
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee's id is " + employeeId;
    }

}

/*
    @Controller vs @RestController  --->  https://www.geeksforgeeks.org/difference-between-controller-and-restcontroller-annotation-in-spring/

    @RequestBody and @ResponseBody  --->  https://www.baeldung.com/spring-request-response-body
    The @RequestBody annotation allows us to retrieve the request's body
    We can then return it as a String or deserialize it into a Plain Old Java Object (POJO)
    Spring has built-in mechanisms for deserializing JSON and XML objects into POJOs which makes this task a lot easier as well
    https://www.udemy.com/course/spring-hibernate-tutorial/learn/lecture/12941122#questions/8123269

    @RequestBody vs @RequestParam  --->  https://stackoverflow.com/questions/28039709/what-is-difference-between-requestbody-and-requestparam
*/