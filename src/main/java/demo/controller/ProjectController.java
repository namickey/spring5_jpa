package demo.controller;

import javax.validation.Valid;

import demo.entity.Project;
import demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(value = "/project/regist/index", method = RequestMethod.GET)
    public String index(Model model){

        List<Project> list = projectRepository.findAll();
        for (Project a : list) {
            System.out.println(a);
        }

        model.addAttribute("projectForm", new ProjectForm());
        return "project/regist";
    }

    @RequestMapping(value = "/project/regist/regist", method = RequestMethod.POST)
    public String regist(@Valid @ModelAttribute("projectForm")ProjectForm projectForm, BindingResult result){

        if(result.hasErrors()){
            return "project/regist";
        }

        Project project = new Project();
        project.setName(projectForm.getName());
        projectRepository.saveAndFlush(project);

        return "redirect:index";
    }
}
