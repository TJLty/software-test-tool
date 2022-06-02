package tj.lty.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tj.lty.backend.model.Batch;
import tj.lty.backend.model.Project;
import tj.lty.backend.model.Result;
import tj.lty.backend.model.TestData;
import tj.lty.backend.service.BatchService;
import tj.lty.backend.service.ProjectService;
import tj.lty.backend.service.TestDataService;

import java.util.List;

@RestController
@RequestMapping("batches")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private TestDataService testDataService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.POST)
    public Result addBatch(@RequestBody Batch batch){
        Integer id= batchService.addBatch(batch);
        Project project=projectService.getProject(batch.getProjectId());
        for(int i=1;i<=project.getProjectBatchSize();i++){
            String key="arg["+Integer.toString(i)+"]";
            String value=batch.getTestData().get(key);
            TestData testData=new TestData();
            testData.setBatchId(batch.getBatchId());
            testData.setDataValue(value);
            testDataService.addTestData(testData);
        }
        return new Result().success("success");
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public Result deleteBatches(@RequestBody List<Integer> batches){
        System.out.println(batches);
        return new Result().success(batchService.deleteBatches(batches));
    }
}
