package demo.service;

import demo.entity.Project;
import demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Transactional
    @Override
    public void resist(String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.saveAndFlush(project);
    }

    @Override
    public void find() {
        List<Project> list = projectRepository.findAll();
        for (Project a : list) {
            System.out.println(a);
        }
    }
}
