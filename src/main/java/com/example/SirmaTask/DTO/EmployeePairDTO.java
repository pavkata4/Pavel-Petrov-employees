package com.example.SirmaTask.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePairDTO {
    private Long empId1;
    private Long empId2;
    private Long projectId;
    private int daysWorked;


    public Long getEmpId1() {
        return empId1;
    }

    public void setEmpId1(Long empId1) {
        this.empId1 = empId1;
    }

    public Long getEmpId2() {
        return empId2;
    }

    public void setEmpId2(Long empId2) {
        this.empId2 = empId2;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }
}
