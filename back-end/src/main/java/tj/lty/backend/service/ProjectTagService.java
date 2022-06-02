package tj.lty.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tj.lty.backend.mapper.ProjectTagMapper;
import tj.lty.backend.model.ProjectTag;

import java.util.Date;
import java.util.List;

@Service
public class ProjectTagService {
    @Autowired
    ProjectTagMapper projectTagMapper;

    public List<ProjectTag> getAllTags(Integer projectId){
        System.out.println(projectId);
        QueryWrapper<ProjectTag> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("project_id",projectId);
        List<ProjectTag> tagList= projectTagMapper.selectList(queryWrapper);
        return tagList;
    }

    public ProjectTag getTag(Integer tagId){
        ProjectTag tag= projectTagMapper.selectById(tagId);
        return tag;
    }

    public Integer addTag(ProjectTag tag){
        tag.setCreateTime(new Date());
        return projectTagMapper.insert(tag);
    }


}
