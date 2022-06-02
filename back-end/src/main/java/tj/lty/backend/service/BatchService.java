package tj.lty.backend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tj.lty.backend.mapper.BatchMapper;
import tj.lty.backend.model.Batch;
import tj.lty.backend.model.Project;

import java.util.List;

@Service
public class BatchService {
    @Autowired
    BatchMapper batchMapper;

    public List<Batch> getProjectBatches(Integer projectId){
        QueryWrapper<Batch> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("project_id",projectId);
        List<Batch> result=batchMapper.selectList(queryWrapper);
        return result;
    }

    public Integer addBatch(Batch batch){
        batchMapper.insert(batch);
        return batch.getBatchId();
    }

    public Integer deleteBatches(List<Integer> batchIdList){
        return batchMapper.deleteBatchIds(batchIdList);
    }

    public Batch getBatch(Integer batchId){
        return batchMapper.selectById(batchId);
    }
}
