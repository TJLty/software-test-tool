package tj.lty.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName("batch")
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
    @TableId(value="batch_id",type= IdType.AUTO)
    private Integer batchId;

    @TableField(value="project_id")
    private Integer projectId;

    @TableField(value="remark")
    private String remark;

    @TableField(exist = false)
    private List<TestData> testDataList;

}
