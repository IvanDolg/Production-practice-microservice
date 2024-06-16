package com.practice.depatmentservice.service.impl;

import com.practice.depatmentservice.dto.DepartmentDto;
import com.practice.depatmentservice.entity.Department;
import com.practice.depatmentservice.mapper.AutoDepartmentMapper;
import com.practice.depatmentservice.repository.DepartmentRepository;
import com.practice.depatmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department saveDepartment = departmentRepository.save(department);

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }
}
