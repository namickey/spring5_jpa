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
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/project/regist/index", method = RequestMethod.GET)
    public String index(Model model){
        projectService.find();

        model.addAttribute("projectForm", new ProjectForm());
        return "project/regist";
    }

    @RequestMapping(value = "/project/regist/regist", method = RequestMethod.POST)
    public String regist(@Valid @ModelAttribute("projectForm")ProjectForm projectForm, BindingResult result){

        if(result.hasErrors()){
            return "project/regist";
        }

        projectService.resist(projectForm.getName());

        return "redirect:index";
    }

    @RequestMapping(value = "/project/regist/registerror", method = RequestMethod.POST)
    public String registError(@Valid @ModelAttribute("projectForm")ProjectForm projectForm, BindingResult result){

        if(result.hasErrors()){
            return "project/regist";
        }

        projectService.resistError(projectForm.getName());

        return "redirect:index";
    }

    @RequestMapping(value = "/project/regist/registchild", method = RequestMethod.POST)
    public String registChild(@Valid @ModelAttribute("projectForm")ProjectForm projectForm, BindingResult result){

        if(result.hasErrors()){
            return "project/regist";
        }

        projectService.resistChild(projectForm.getName());

        return "redirect:index";
    }
}
