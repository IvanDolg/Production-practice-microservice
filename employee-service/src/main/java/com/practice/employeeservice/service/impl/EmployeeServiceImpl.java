package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.APIResponseDto;
import com.practice.employeeservice.dto.DepartmentDto;
import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.dto.OrganizationDto;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.exception.EmailAlreadyExistsException;
import com.practice.employeeservice.exception.RecurseNotFoundException;
import com.practice.employeeservice.mapper.AutoEmployeeMapper;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.APIClient;
import com.practice.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static  final  Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private  EmployeeRepository employeeRepository;

    //private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;

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

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("instance getEmployeeById() method");
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

        //DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        OrganizationDto organizationDto =webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RecurseNotFoundException("Employee", "id", employeeId)
        );
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setOrganizationCode(employeeDto.getOrganizationCode());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDepartmentCode(employeeDto.getDepartmentCode());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RecurseNotFoundException("Employee", "id", employeeId)
        );
        employeeRepository.delete(employee);
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("instance getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RecurseNotFoundException("Employee", "id", employeeId)
        );

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Default Department");
        departmentDto.setDepartmentDescription("Default Description");
        departmentDto.setDepartmentCode("Default Code");

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}