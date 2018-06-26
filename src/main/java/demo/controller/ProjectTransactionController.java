package demo.controller;

import javax.validation.Valid;

import demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProjectTransactionController {

    @Autowired
    ProjectService projectService;

    @ModelAttribute(name = "projectFormCommit")
    public ProjectForm setProjectFormCommit(){
        return new ProjectForm();
    }

    @ModelAttribute(name = "projectFormRollback")
    public ProjectForm setProjectFormRollback(){
        return new ProjectForm();
    }

    @ModelAttribute(name = "projectForm")
    public ProjectForm setProjectForm(){
        return new ProjectForm();
    }

    @RequestMapping(value = "/project/transaction/index", method = RequestMethod.GET)
    public String index(){
        projectService.find();
        return "project/transaction";
    }

    @RequestMapping(value = "/project/transaction/registWithCommit", method = RequestMethod.POST)
    public String registWithCommit(@Valid @ModelAttribute("projectFormCommit")ProjectForm form, BindingResult result){

        if(result.hasErrors()){
            return "project/transaction";
        }

        projectService.resist(form.getProjectName(), form.getMemberName());

        return "redirect:index";
    }

    @RequestMapping(value = "/project/transaction/registWithRollback", method = RequestMethod.POST)
    public String registWithRollback(@Valid @ModelAttribute("projectFormRollback")ProjectForm form, BindingResult result){

        if(result.hasErrors()){
            return "project/transaction";
        }

        projectService.resistError(form.getProjectName(), form.getMemberName());

        return "redirect:index";
    }

    @RequestMapping(value = "/project/transaction/registchild", method = RequestMethod.POST)
    public String registChild(@Valid @ModelAttribute("projectForm")ProjectForm projectForm, BindingResult result){

        if(result.hasErrors()){
            return "project/transaction";
        }

        projectService.resistChild(projectForm.getProjectName());

        return "redirect:index";
    }
}
