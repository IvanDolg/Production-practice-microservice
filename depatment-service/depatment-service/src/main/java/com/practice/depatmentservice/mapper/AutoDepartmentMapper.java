package com.practice.depatmentservice.mapper;

import com.practice.depatmentservice.dto.DepartmentDto;
import com.practice.depatmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoDepartmentMapper {
    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);

    DepartmentDto mapToDepartmentDto (Department department);
    Department mapToDepartment(DepartmentDto departmentDto);
}
