package com.example.SirmaTask.Controller;

import com.example.SirmaTask.DTO.EmployeePairDTO;
import com.example.SirmaTask.Exception.NoPairFoundException;
import com.example.SirmaTask.Service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")

public class EmployeeProjectController {

    @Autowired
    private final EmployeeProjectService employeeProjectService;

    public EmployeeProjectController(EmployeeProjectService employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }

    @GetMapping("/result")
    public ResponseEntity<EmployeePairDTO> getResult() {
        EmployeePairDTO result = null;
        try {
            result = employeeProjectService.pair();
        } catch (NoPairFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
