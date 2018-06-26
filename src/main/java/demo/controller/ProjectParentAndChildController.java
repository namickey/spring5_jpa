package demo.controller;

import demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProjectParentAndChildController {

    @Autowired
    ProjectService projectService;

    @ModelAttribute(name = "projectForm")
    public ProjectForm setProjectForm(){
        return new ProjectForm();
    }

    @RequestMapping(value = "/project/child/index", method = RequestMethod.GET)
    public String index(){
        projectService.find();
        return "project/parentAndChild";
    }

    @RequestMapping(value = "/project/child/registParentAndChild", method = RequestMethod.POST)
    public String registChild(@Valid @ModelAttribute("projectForm")ProjectForm form, BindingResult result){

        if(result.hasErrors()){
            return "project/parentAndChild";
        }

        projectService.resistChild(form.getProjectName(), form.getMemberName());

        return "redirect:index";
    }
}
