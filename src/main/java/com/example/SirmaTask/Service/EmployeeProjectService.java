package com.example.SirmaTask.Service;

import com.example.SirmaTask.DTO.EmployeePairDTO;
import com.example.SirmaTask.Entity.EmployeeProject;
import com.example.SirmaTask.Exception.NoPairFoundException;
import com.example.SirmaTask.Repository.EmployeeProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmployeeProjectService {

    private final EmployeeProjectRepository employeeProjectRepository;

    public EmployeeProjectService(EmployeeProjectRepository employeeProjectRepository) {
        this.employeeProjectRepository = employeeProjectRepository;
    }

    public void save(EmployeeProject employeeProject) {
        employeeProjectRepository.saveAndFlush(employeeProject);
    }

    public EmployeePairDTO pair() throws NoPairFoundException {
        List<Object[]> result = employeeProjectRepository.findLongestWorkingPair();

        if (result == null) {
            throw new NoPairFoundException("No pair of employees found working together for the longest time.");
        }
        EmployeePairDTO employeePairDTO = new EmployeePairDTO();
        for (Object[] row : result) {
            Long empId1 = Long.parseLong(row[0].toString());
            Long empId2 = Long.parseLong(row[1].toString());
            Long projectId = Long.parseLong(row[2].toString());
            int daysWorked = Integer.parseInt(row[3].toString());
            employeePairDTO.setEmpId1(empId1);
            employeePairDTO.setEmpId2(empId2);
            employeePairDTO.setProjectId(projectId);
            employeePairDTO.setDaysWorked(daysWorked);
        }

        return employeePairDTO;

    }
}
