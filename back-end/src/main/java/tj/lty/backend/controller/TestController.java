package tj.lty.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tj.lty.backend.model.*;
import tj.lty.backend.service.BatchService;
import tj.lty.backend.service.ProjectService;
import tj.lty.backend.service.ProjectTagService;
import tj.lty.backend.service.TestDataService;
import tj.lty.backend.utils.TestPythonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {
    @Autowired
    private BatchService batchService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TestDataService testDataService;

    @Autowired
    private ProjectTagService projectTagService;

    @RequestMapping(value = "python",method= RequestMethod.POST)
    public Result startTest(@RequestBody List<Integer> batchIds,@RequestParam("tag") Integer tagId){
        List<Batch> batches=new ArrayList<>();
        ProjectTag tag= projectTagService.getTag(tagId);
        if(batchIds.isEmpty()){
            return new Result().fail("测试数据为空");
        }

        HashMap<Integer,List<String>> data = new HashMap<>();

        for (int id:batchIds){
            List<String> temp=new ArrayList<>();
            List<TestData> testDataList=testDataService.getTestData(id);
            for(TestData testData:testDataList){
                temp.add(testData.getDataValue());
            }
            data.put(id,temp);
            batches.add(batchService.getBatch(id));
        }
        Project project=projectService.getProject(batches.get(0).getProjectId());
        if(!Objects.equals(tag.getProjectId(), project.getProjectId())){
            return new Result().fail("标签和测试数据项目不匹配");
        }
        TestPythonUtils testPythonUtils=new TestPythonUtils();
        testPythonUtils.setTestDataSet(data);
        testPythonUtils.setTestBatches(batches);
        testPythonUtils.setProject(project);
        testPythonUtils.setTag(tag);
        return new Result().success(testPythonUtils.startTests());
    }

}