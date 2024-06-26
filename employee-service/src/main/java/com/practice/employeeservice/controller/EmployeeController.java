package com.practice.employeeservice.controller;

import com.practice.employeeservice.dto.APIResponseDto;
import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Controller",
        description = "Employee controller exposes REST APIs for Employee-Service"
)
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(
            summary = "Create new employee",
            description = "Used to save new employee to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Employee created"
            )
    )
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get employee by id",
            description = "Used to retrieve employee from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee found"
            )
    )
    @GetMapping("/{employee-id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("employee-id") Long employeeId) {
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update employee",
            description = "Used to update employee in a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee updated"
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestBody EmployeeDto employeeDto,
            @PathVariable("id") Long employeeId) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto, employeeId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete employee",
            description = "Used to delete employee from a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "Employee deleted"
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
