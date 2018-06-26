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

    public void find() {
        List<Project> list = projectRepository.findJoin();
        for (Project a : list) {
            System.out.println(a);
        }

        memberService.find();
    }

    @Transactional
    public void resist(String projectName, String memberName) {
        Project project = new Project();
        project.setName(projectName);
        projectRepository.save(project);

        memberService.resist(memberName);
    }

    @Transactional
    public void resistError(String projectName, String memberName) {
        Project project = new Project();
        project.setName(projectName);
        projectRepository.save(project);

        try {
            memberService.resistError(memberName);
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
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
}
