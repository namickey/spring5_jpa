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

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Transactional
    @Override
    public void resist(String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.save(project);

        memberServiceImpl.resist(name + " mem");
    }

    @Transactional
    @Override
    public void resistError(String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.save(project);

        try {
            memberServiceImpl.resistError(name + " mem");
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void find() {
        List<Project> list = projectRepository.findAll();
        for (Project a : list) {
            System.out.println(a);
        }

        memberServiceImpl.find();
    }
}
