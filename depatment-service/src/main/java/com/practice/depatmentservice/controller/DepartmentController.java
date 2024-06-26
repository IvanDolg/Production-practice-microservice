package com.practice.depatmentservice.controller;

import com.practice.depatmentservice.dto.DepartmentDto;
import com.practice.depatmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Department Controller",
        description = "Department controller exposes rest apis for department-service"
)
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @Operation(
            summary = "Create new department",
            description = "Used to save new department to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Department created"
            )
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto saveDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get department by id",
            description = "Used to retrieve department from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Department found"
            )
    )
    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable ("department-code" ) String departmentCode) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update department",
            description = "Used to update department in db"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Department updated"
            )
    )
    @PutMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @RequestBody DepartmentDto departmentDto,
            @PathVariable ("department-code" ) String departmentCode) {

        DepartmentDto updateDepartment = departmentService.updateDepartment(departmentDto, departmentCode);
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete department",
            description = "Used to delete department from db"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "Department deleted"
            )
    )
    @DeleteMapping("/{department-code}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("department-code" ) String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
