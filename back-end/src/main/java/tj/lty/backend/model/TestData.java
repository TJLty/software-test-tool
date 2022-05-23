package tj.lty.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@lombok.Data
@TableName("test_data")
public class TestData {
    @TableId(value="data_id",type= IdType.AUTO)
    private Integer dataId;

    @TableField(value="batch_id")
    private Integer batchId;

    @TableField(value="data_value")
    private String dataValue;
}
