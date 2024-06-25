package com.practice.organizationservice.service;

import com.practice.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);
    OrganizationDto updateOrganization(OrganizationDto organizationDto, String organizationCode);
    void deleteOrganization(String organizationCode);
}
