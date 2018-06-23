package demo.controller;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProjectForm {

	@Size(max = 20)
    private String name;
}
