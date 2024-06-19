package com.practice.depatmentservice.service.impl;

import com.practice.depatmentservice.dto.DepartmentDto;
import com.practice.depatmentservice.entity.Department;
import com.practice.depatmentservice.exception.DepartmentCodeAlreadyExistsException;
import com.practice.depatmentservice.exception.RecurseNotFoundException;
import com.practice.depatmentservice.mapper.AutoDepartmentMapper;
import com.practice.depatmentservice.repository.DepartmentRepository;
import com.practice.depatmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());

        if (optionalDepartment.isPresent()) {
            throw new DepartmentCodeAlreadyExistsException("Department already exists for this code");
        }

        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department saveDepartment = departmentRepository.save(department);

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {


        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(
                () -> new RecurseNotFoundException("Department", "departmentCode", departmentCode)
        );

       return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }
}
