package com.example.SirmaTask.Service;

import com.example.SirmaTask.Entity.Employee;
import com.example.SirmaTask.Entity.EmployeeProject;
import com.example.SirmaTask.Entity.Project;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service


public class FileService {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    private final EmployeeProjectService employeeProjectService;

    @Autowired
    private final ProjectService projectService;

    public FileService(EmployeeService employeeService, EmployeeProjectService employeeProjectService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.employeeProjectService = employeeProjectService;
        this.projectService = projectService;
    }

    public void parseCsv(MultipartFile file) {

        List<EmployeeProject> employeeProjects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build())) {

            for (CSVRecord csvRecord : csvParser) {
                Long empId = Long.parseLong(csvRecord.get("EmpId"));
                Long projectId = Long.parseLong(csvRecord.get("ProjectId"));
                LocalDate dateFrom = LocalDate.parse(csvRecord.get("DateFrom"));
                LocalDate dateTo = csvRecord.get("DateTo").isEmpty() ? null : LocalDate.parse(csvRecord.get("DateTo"));

                Employee employee = employeeService.findById(empId).orElse(employeeService.saveEmployee(empId));
                Project project = projectService.findById(projectId).orElse(projectService.saveProject(projectId));

                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setEmployee(employee);
                employeeProject.setProject(project);
                employeeProject.setDateFrom(dateFrom);
                employeeProject.setDateTo(dateTo);

                employeeProjectService.save(employeeProject);
                employeeProjectService.pair();
            }

        } catch (Exception e) {
            System.err.println("Error processing CSV file: " + e.getMessage());
        }


    }

}

