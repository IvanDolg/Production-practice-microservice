package com.practice.depatmentservice.service.impl;

import com.practice.depatmentservice.dto.DepartmentDto;
import com.practice.depatmentservice.entity.Department;
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
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        Department saveDepartment = departmentRepository.save(department);

        return new DepartmentDto(
          saveDepartment.getId(),
          saveDepartment.getDepartmentName(),
          saveDepartment.getDepartmentDescription(),
          saveDepartment.getDepartmentCode()
        );
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return new DepartmentDto(
          department.getId(),
          department.getDepartmentName(),
          department.getDepartmentDescription(),
          department.getDepartmentCode()
        );

    }
}
