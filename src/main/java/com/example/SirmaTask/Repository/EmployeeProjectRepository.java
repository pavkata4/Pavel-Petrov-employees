package com.example.SirmaTask.Repository;

import com.example.SirmaTask.Entity.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {

    @Query(value = "SELECT \n" +
            "    ep1.emp_id AS empId1, \n" +
            "    ep2.emp_id AS empId2, \n" +
            "    ep1.project_id, \n" +
            "    LEAST(COALESCE(ep1.date_to, CURRENT_DATE), COALESCE(ep2.date_to, CURRENT_DATE)) - GREATEST(COALESCE(ep1.date_from, '1970-01-01'), COALESCE(ep2.date_from, '1970-01-01')) AS daysWorked \n" +
            "FROM \n" +
            "    employee_projects ep1 \n" +
            "JOIN \n" +
            "    employee_projects ep2 ON ep1.project_id = ep2.project_id \n" +
            "WHERE \n" +
            "    ep1.emp_id < ep2.emp_id \n" +
            "GROUP BY \n" +
            "    ep1.emp_id, ep2.emp_id, ep1.project_id, ep1.date_to, ep2.date_to, ep1.date_from, ep2.date_from \n" +
            "HAVING \n" +
            "    LEAST(COALESCE(ep1.date_to, CURRENT_DATE), COALESCE(ep2.date_to, CURRENT_DATE)) - GREATEST(COALESCE(ep1.date_from, '1970-01-01'), COALESCE(ep2.date_from, '1970-01-01')) > 0 \n" +
            "ORDER BY \n" +
            "    daysWorked DESC \n" +
            "LIMIT 1;\n", nativeQuery = true)
    List<Object[]> findLongestWorkingPair();

}

