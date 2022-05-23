package tj.lty.backend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tj.lty.backend.mapper.ProjectMapper;
import tj.lty.backend.model.Project;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public List<Project> getAllProjects(){
        List<Project> projectList=projectMapper.selectList(null);
        System.out.println(projectList);
        return projectList;
    }

    public Project getNewestProjects(){
        LambdaQueryWrapper<Project> queryWrapper= Wrappers.<Project>lambdaQuery();
        queryWrapper.orderByDesc(Project::getProjectId);
        queryWrapper.last("limit 1");
        List<Project> projectList=projectMapper.selectList(queryWrapper);
        return projectList.get(0);
    }

    public Project getProject(Integer id){
        return projectMapper.selectById(id);
    }

    public Integer addProject(Project project){
        project.setProjectCreateTime(new Date());
        return projectMapper.insert(project);
    }
}
