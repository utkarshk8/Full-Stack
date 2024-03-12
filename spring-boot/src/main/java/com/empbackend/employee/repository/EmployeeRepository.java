package com.empbackend.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empbackend.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
