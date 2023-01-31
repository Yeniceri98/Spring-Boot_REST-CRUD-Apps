package com.springboot.restcrudhibernateapp.service;

import com.springboot.restcrudhibernateapp.dao.EmployeeRepository;
import com.springboot.restcrudhibernateapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;      // NOT: Diğer DAO implementasyonlarında employeeDAO inject edilmiş ve aşağıdaki metodlarda kullanılmıştı. Artık böyle bir impelementasyonumuz yok

    // Constuctor Injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    // @Transactional       // NOT: JpaRepository transaction işlemini otomatikman hallettiği için @Transactional anotasyonunu koymaya gerek kalmaz. Diğer metodlarda da koymadık
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    // NOT: Bu metod bazında "Optional" değişikliği yapmak gerekir. https://stackoverflow.com/questions/49316751/spring-data-jpa-findone-change-to-optional-how-to-use-this
    public Employee findById(int theId) {

        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Couldn't find employee with the id of " + theId);
        }

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}

/*
    Application Architecture:
    Employee REST Controller  <----->  Employee Service  <----->  Employee DAO - Hibernate  <----->  Database

    @Transactional anotasyonunu DAO'dan alıp burada tanımladık. Best practice olarak Service layer'ında transaction'ın açılıp kapanması önerilir. Her metod için eklenmelidir
    Metodlarda DAO'ya yönlendirme yapıyoruz
*/
