package com.example.SirmaTask.Service;

import com.example.SirmaTask.Entity.Employee;
import com.example.SirmaTask.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findById(Long empId) {
        return employeeRepository.findById(empId);
    }

    public Employee saveEmployee(Long empId) {
        Employee employee = new Employee();
        employee.setEmpId(empId);
        return employeeRepository.saveAndFlush(employee);
    }
}
