package tj.lty.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tj.lty.backend.model.Project;
import tj.lty.backend.model.Result;
import tj.lty.backend.service.ProjectService;
import tj.lty.backend.utils.FileUtils;

import java.util.Date;
import java.util.Objects;

@RestController()
@RequestMapping("/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    FileUtils fileUtils;

    @RequestMapping()
    public Result getAllProjects() {
        return new Result().success(projectService.getAllProjects());
    }

    @RequestMapping("/newest")
    public Result getNewestProjects() {
        return new Result().success(projectService.getNewestProjects());
    }

    @RequestMapping("/{id}")
    public Result getProject(@PathVariable Integer id) {
        return new Result().success(projectService.getProject(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result addProject(@RequestParam("name") String name, @RequestParam("intro") String intro, @RequestParam("type") String type, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "url", required = false) String url,@RequestParam(value="size") Integer size) {
        Project project=new Project();
        project.setProjectName(name);
        project.setProjectIntro(intro);
        project.setProjectType(type);
        project.setProjectCreateTime(new Date());
        project.setProjectBatchSize(size);
        System.out.println(file.getOriginalFilename());
        if(Objects.equals(type, "python")){
            String filePath=fileUtils.saveFile(file);
            project.setFilePath(filePath);
        }else if(Objects.equals(type,"api")){
            project.setUrl(url);
        }
        return new Result().success(projectService.addProject(project));
    }
}
