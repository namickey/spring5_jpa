package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.ProjectDto;
import demo.service.ProjectService;

@RestController
public class RestProjectController {

	@Autowired
	ProjectService projectService;

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public List<ProjectDto> getProjectList() {
		return projectService.getList();
	}
}
