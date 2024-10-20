package com.example.SirmaTask.Service;

import com.example.SirmaTask.Entity.Project;
import com.example.SirmaTask.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ProjectService {
    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public Project saveProject(Long projectId) {
        Project project = new Project();
        project.setId(projectId);
        return projectRepository.saveAndFlush(project);
    }
}
