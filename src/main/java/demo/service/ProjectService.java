package demo.service;

import demo.entity.Member;
import demo.entity.Project;
import demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MemberService memberService;

    @Transactional
    public void resist(String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.save(project);

        memberService.resist(name + " mem");
    }

    @Transactional
    public void resistChild(String name) {
        Project project = new Project();
        project.setName(name);

        IntStream.range(0, 2).forEach(i -> {
            Member member = new Member();
            member.setName(name+i);
            project.getMemberList().add(member);
            member.setProject(project);
        });

        projectRepository.save(project);
    }

    @Transactional
    public void resistError(String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.save(project);

        try {
            memberService.resistError(name + " mem");
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void find() {
        List<Project> list = projectRepository.findJoin();
        for (Project a : list) {
            System.out.println(a);
        }

        memberService.find();
    }
}
