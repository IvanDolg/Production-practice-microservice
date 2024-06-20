package com.practice.organizationservice.mapper;

import com.practice.organizationservice.dto.OrganizationDto;
import com.practice.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoOrganizationMapper {
    AutoOrganizationMapper MAPPER = Mappers.getMapper(AutoOrganizationMapper.class);

    OrganizationDto mapToOrganizationDto(Organization organization);
    Organization mapToOrganization(OrganizationDto organizationDto);
}
