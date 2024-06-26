package com.practice.employeeservice.service;

import com.practice.employeeservice.dto.APIResponseDto;
import com.practice.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId);
    void deleteEmployee(Long employeeId);
}
