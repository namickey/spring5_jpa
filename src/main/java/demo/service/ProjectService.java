package demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dto.ProjectDto;
import demo.entity.Member;
import demo.entity.Project;
import demo.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MemberService memberService;

    @Transactional
    public List<Project> find() {
        List<Project> list = projectRepository.findJoin();
        list.forEach(System.out::println);
        List<Project> list2 = projectRepository.findJoin();
        list2.forEach(System.out::println);

        try {
            GroovyScriptEngine gse = new GroovyScriptEngine("/home/dai/seino");
            gse.run("requestOne.groovy", "");
//            import groovyx.net.http.HTTPBuilder
//            println "hello world!!"
//            def http = new HTTPBuilder('http://localhost:8080')
//
//            http.get([path : '/demo/project']) { resp, reader ->
//                    println(reader)
//            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        List<Member> memlist = memberService.find();
        memlist.forEach(System.out::println);
        return list;
    }

    @Transactional
    public List<ProjectDto> getList(){

        List<ProjectDto> list = new ArrayList<>();

        projectRepository.findAll().forEach(projectDto ->{
            ProjectDto dto = new ProjectDto();
            BeanUtils.copyProperties(projectDto, dto);
            list.add(dto);
        });

        return list;
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
    public void resistChild(String projectName, String memberName) {
        Project project = new Project();
        project.setName(projectName);

        IntStream.range(0, 2).forEach(i -> {
            Member member = new Member();
            member.setName(memberName+i);
            project.getMemberList().add(member);
            member.setProject(project);
        });

        projectRepository.save(project);
    }
}
