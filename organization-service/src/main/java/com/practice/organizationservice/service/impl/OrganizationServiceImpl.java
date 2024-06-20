package com.practice.organizationservice.service.impl;

import com.practice.organizationservice.dto.OrganizationDto;
import com.practice.organizationservice.entity.Organization;
import com.practice.organizationservice.mapper.AutoOrganizationMapper;
import com.practice.organizationservice.repository.OrganizationRepository;
import com.practice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = AutoOrganizationMapper.MAPPER.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);

        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }
}
