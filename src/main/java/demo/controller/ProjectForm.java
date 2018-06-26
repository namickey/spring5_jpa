package demo.controller;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProjectForm {

	@Size(max = 10)
    private String projectName;

	@Size(max = 10)
    private String memberName;
}
