package tj.lty.backend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class TestResult implements Serializable {
    private Integer batchId;
    private String expectAnswer;
    private String answer;
    private Boolean result;
}
