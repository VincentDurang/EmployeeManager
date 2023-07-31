package com.example.employeemanager.service;

import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void ajouter( Employee employee){
        this.employeeRepository.save(employee);
    }

    public Optional<Employee> GetIdByEmployee(Long id){
       return this.employeeRepository.findById(id.toString());
    }

    public void modifier(Employee employee){
            employeeRepository.save(employee);
    }

    public void supp(Long id){
        employeeRepository.deleteById(id.toString());
    }

}
