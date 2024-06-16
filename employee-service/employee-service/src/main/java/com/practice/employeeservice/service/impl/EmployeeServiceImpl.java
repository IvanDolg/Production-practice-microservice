package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.mapper.AutoEmployeeMapper;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private  EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }
}
