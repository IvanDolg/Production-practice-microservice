package com.practice.depatmentservice.service;

import com.practice.depatmentservice.dto.DepartmentDto;

public interface  DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String departmentCode);
    DepartmentDto updateDepartment(DepartmentDto departmentDto, String departmentCode);
    void deleteDepartment(String departmentCode);
}
