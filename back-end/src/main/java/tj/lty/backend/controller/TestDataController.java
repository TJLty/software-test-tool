package tj.lty.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tj.lty.backend.model.Batch;
import tj.lty.backend.model.Result;
import tj.lty.backend.model.TestData;
import tj.lty.backend.service.BatchService;
import tj.lty.backend.service.TestDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/data")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestDataController {
    @Autowired
    TestDataService testDataService;

    @Autowired
    BatchService batchService;

    @RequestMapping()
    public Result getAllData(Integer projectId){
        List<Batch> dataList;
        dataList=batchService.getProjectBatches(projectId);
        for(Batch batch:dataList){
            List<TestData> testDataList=testDataService.getTestData(batch.getBatchId());
            HashMap<String,String> temp=new HashMap<>();
            int i=1;
            for(TestData testData:testDataList){
                temp.put("arg["+Integer.toString(i)+"]",testData.getDataValue());
                i++;
            }
            batch.setTestData(temp);
        }
        return new Result().success(dataList);
    }
}
