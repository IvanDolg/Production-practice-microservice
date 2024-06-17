package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.exception.EmailAlreadyExistsException;
import com.practice.employeeservice.exception.RecurseNotFoundException;
import com.practice.employeeservice.mapper.AutoEmployeeMapper;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private  EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RecurseNotFoundException("Employee", "id", employeeId)
        );

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }
}
