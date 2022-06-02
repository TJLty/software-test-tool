package tj.lty.backend.utils;

import lombok.Data;
import tj.lty.backend.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
public class TestPythonUtils {
    private Project project;

    private ProjectTag tag;
    private HashMap<Integer,List<String>> testDataSet;
    private List<Batch> testBatches;

    public TestSummary startTests() {
        TestSummary testSummary=new TestSummary();
        int totalNumber=0;
        int correctNumber=0;
        List<TestResult> testDetail=new ArrayList<>();
        for(Batch batch:testBatches){
            TestResult testResult=new TestResult();
            testResult.setBatchId(batch.getBatchId());
            String temp=startSingleTest(testDataSet.get(batch.getBatchId()));
            if(Objects.equals(temp, batch.getAnswer())){
                correctNumber++;
                testResult.setResult(true);
            }else {
                testResult.setResult(false);
            }
            testResult.setAnswer(temp);
            testResult.setExpectAnswer(batch.getAnswer());
            testDetail.add(testResult);
            totalNumber++;
        }
        testSummary.setTotalNumber(totalNumber);
        testSummary.setCorrectNumber(correctNumber);
        testSummary.setTestDetail(testDetail);
        return testSummary;
    }

    public String startSingleTest(List<String> dataSet){
        String cmd="python "+tag.getFilePath();
        System.out.println(cmd);
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmd);

            OutputStream pOutputStream =process.getOutputStream();

            PrintWriter outputWriter = new PrintWriter(pOutputStream);
            for(String data:dataSet){
                System.out.println(data);
                outputWriter.print(data+"\n");
                outputWriter.flush();
            }
            InputStream inputStream = process.getInputStream();
            byte[] arr=new byte[1024*1024*100];
            System.out.println(arr);
            int len = inputStream.read(arr);
            System.out.println(arr);
            System.out.println(len);
            String ans;
            String os=System.getProperty("os.name");
            System.out.println(os);
            if (os != null && os.toLowerCase().startsWith("windows")) {
                ans=new String(arr, 0, len, "GBK");
//                System.out.println(new String(arr, 0, len, "GBK"));
            } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
                ans=new String(arr, 0, len, StandardCharsets.UTF_8);
//                System.out.println(new String(arr, 0, len, StandardCharsets.UTF_8));
            } else { //其它操作系统
                ans=new String(arr, 0, len, StandardCharsets.UTF_8);
//                System.out.println(new String(arr, 0, len, StandardCharsets.UTF_8));
            }
            return ans.replaceAll("[\r\n]","");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
