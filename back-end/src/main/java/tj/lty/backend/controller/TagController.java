package tj.lty.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tj.lty.backend.model.Project;
import tj.lty.backend.model.Result;
import tj.lty.backend.model.ProjectTag;
import tj.lty.backend.service.ProjectService;
import tj.lty.backend.service.ProjectTagService;
import tj.lty.backend.utils.FileUtils;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/tags")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {
    @Autowired
    ProjectTagService projectTagService;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ProjectService projectService;

    @RequestMapping()
    public Result getAllProjectTags(@RequestParam("project_id") Integer projectId){
        return new Result().success(projectTagService.getAllTags(projectId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result addProjectTag(@RequestParam("project_id")Integer projectId, @RequestParam("tag")String tag, @RequestParam(value="file",required = false)MultipartFile file, @RequestParam(value = "url", required = false) String url){

        ProjectTag newProjectTag=new ProjectTag();
        newProjectTag.setTag(tag);
        newProjectTag.setProjectId(projectId);
        Project project=projectService.getProject(projectId);

        if(Objects.equals(project.getProjectType(), "python")){
            String filePath=fileUtils.saveFile(file);
            newProjectTag.setFilePath(filePath);
        }else if(Objects.equals(project.getProjectType(),"api")){
            newProjectTag.setUrl(url);
        }
        return new Result().success(projectTagService.addTag(newProjectTag));
    }
}
