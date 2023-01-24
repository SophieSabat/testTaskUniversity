package com.example.testtask.services;

import com.example.testtask.dao.DepartmentDAO;
import com.example.testtask.models.Department;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepartmentService {
    private DepartmentDAO departmentDAO;
    public DepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public void add(Department department) {
        if (department != null) {
            departmentDAO.addDepartment(department);
        }
    }

    public String headOfDepartment(String departmentName) {
        return departmentDAO.whoIsHeadOfDepartment(departmentName);
    }

    public String showDepartmentStatistics(String departmentName) {
        return departmentDAO.showDepartmentStatistics(departmentName);
    }

    public BigDecimal averageSalary(String departmentName) {
        return departmentDAO.averageSalary(departmentName);
    }

    public String countOfEmployeeForDepartment(String departmentName) {
        return departmentDAO.showCountOfEmployeeForDepartment(departmentName);
    }
}
