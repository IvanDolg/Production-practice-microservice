package com.practice.organizationservice.controller;

import com.practice.organizationservice.dto.OrganizationDto;
import com.practice.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Organization Controller",
        description = "Organization controller exposes rest apis for organization-service"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    @Operation(
            summary = "Create new organization",
            description = "Used to save new organization to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Organization created"
            )
    )
    @PostMapping
    private ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
          OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
          return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get organization by id",
            description = "Used to retrieve organization from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Organization found"
            )
    )
    @GetMapping("{organization-code}")
    private ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable ("organization-code" ) String organizationCode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }

    @Operation(
            summary = "Update organization by id",
            description = "Used to update organization in db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Organization updated"
            )
    )
    @PutMapping("{organization-code}")
    private ResponseEntity<OrganizationDto> updateOrganization(
            @RequestBody OrganizationDto organizationDto,
            @PathVariable ("organization-code" ) String organizationCode) {
        OrganizationDto updatedOrganization = organizationService.updateOrganization(organizationDto, organizationCode);
        return ResponseEntity.ok(updatedOrganization);
    }

    @Operation(
            summary = "Delete organization by id",
            description = "Used to delete organization from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "Organization deleted"
            )
    )
    @DeleteMapping("{organization-code}")
    private ResponseEntity<OrganizationDto> deleteOrganization(
            @PathVariable ("organization-code") String organizationCode) {
        organizationService.deleteOrganization(organizationCode);
        return ResponseEntity.ok().build();
    }
}
