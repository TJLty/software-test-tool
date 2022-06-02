package tj.lty.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import tj.lty.backend.mapper.BatchMapper;
import tj.lty.backend.mapper.TestDataMapper;
import tj.lty.backend.model.Result;
import tj.lty.backend.model.TestData;

import java.util.List;

@Service
public class TestDataService {
    @Autowired
    TestDataMapper testDataMapper;

    @Autowired
    BatchMapper batchMapper;

    public List<TestData> getTestData(Integer batchId){
        QueryWrapper<TestData> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("batch_id",batchId);
        List<TestData> result=testDataMapper.selectList(queryWrapper);
        return result;
    }

    public void addTestData(TestData testData){
        testDataMapper.insert(testData);
    }
}
