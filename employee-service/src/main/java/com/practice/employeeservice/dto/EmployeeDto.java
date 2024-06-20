package com.practice.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Response employee dto model information"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private Long id;

    @Schema(
            description = "First name of the employee"
    )
    private String firstName;

    @Schema(
            description = "Last name of the employee"
    )
    private String lastName;

    @Schema(
            description = "Email of the employee"
    )
    private String email;

    @Schema(
            description = "Code of the employee department"
    )
    private String departmentCode;
    private String organizationCode;
}
