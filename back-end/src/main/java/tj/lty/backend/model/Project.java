package tj.lty.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@TableName("project")
public class Project {

    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;
    @TableField(value = "project_name")
    private String projectName;
    @TableField(value = "project_intro")
    private String projectIntro;
    @TableField(value = "project_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date projectCreateTime;
    @TableField(value = "project_type")
    private String projectType;
    @TableField(value="project_batch_size")
    private Integer projectBatchSize;
    @TableField(exist = false)
    private List<Batch> batchList;
    @TableField(exist = false)
    private List<ProjectTag> tagList;

}
