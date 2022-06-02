package tj.lty.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tj.lty.backend.model.Project;
import tj.lty.backend.model.ProjectTag;
import tj.lty.backend.model.Result;
import tj.lty.backend.service.ProjectService;
import tj.lty.backend.service.ProjectTagService;
import tj.lty.backend.utils.FileUtils;

import java.util.Date;

@RestController()
@RequestMapping("/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ProjectTagService projectTagService;

    @RequestMapping()
    public Result getAllProjects() {
        return new Result().success(projectService.getAllProjects());
    }

    @RequestMapping("/newest")
    public Result getNewestProjects() {
        Project project=projectService.getNewestProjects();
        project.setTagList(projectTagService.getAllTags(project.getProjectId()));
        return new Result().success(project);
    }

    @RequestMapping("/{id}")
    public Result getProject(@PathVariable Integer id) {
        Project project=projectService.getProject(id);
        project.setTagList(projectTagService.getAllTags(id));
        return new Result().success(project);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result addProject(@RequestParam("name") String name, @RequestParam("intro") String intro, @RequestParam("type") String type,@RequestParam(value="size") Integer size) {
        Project project=new Project();
        project.setProjectName(name);
        project.setProjectIntro(intro);
        project.setProjectType(type);
        project.setProjectCreateTime(new Date());
        project.setProjectBatchSize(size);
        return new Result().success(projectService.addProject(project));
    }
}
