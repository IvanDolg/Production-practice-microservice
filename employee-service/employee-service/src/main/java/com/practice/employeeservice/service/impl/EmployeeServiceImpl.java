package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.APIResponseDto;
import com.practice.employeeservice.dto.DepartmentDto;
import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.exception.EmailAlreadyExistsException;
import com.practice.employeeservice.exception.RecurseNotFoundException;
import com.practice.employeeservice.mapper.AutoEmployeeMapper;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private  EmployeeRepository employeeRepository;

    //private RestTemplate restTemplate;
    private WebClient webClient;

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
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RecurseNotFoundException("Employee", "id", employeeId)
        );

//        ResponseEntity <DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/departments" +
//                employee.getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = response.getBody();

        DepartmentDto departmentDto =webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}