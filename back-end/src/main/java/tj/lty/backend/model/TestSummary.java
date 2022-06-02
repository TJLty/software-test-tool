package tj.lty.backend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TestSummary implements Serializable {
    private Integer totalNumber;
    private Integer correctNumber;
    private List<TestResult> testDetail;
}
