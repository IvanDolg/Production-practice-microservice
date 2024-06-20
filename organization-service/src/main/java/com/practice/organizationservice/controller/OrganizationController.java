package com.practice.organizationservice.controller;

import com.practice.organizationservice.dto.OrganizationDto;
import com.practice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    private ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
          OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
          return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{organization-code}")
    private ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable ("organization-code" ) String organizationCode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
}
