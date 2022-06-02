package tj.lty.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("project_tag")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTag implements Serializable {
    @TableId(value="tag_id",type= IdType.AUTO)
    private Integer tagId;

    @TableField(value="project_id")
    private Integer projectId;
    @TableField(value="tag")
    private String tag;
    @TableField(value="file_path")
    private String filePath;
    @TableField(value="url")
    private String url;
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
