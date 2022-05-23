package tj.lty.backend.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "path")
@Data
public class FileProperty {
    private String windows;
    private String linux;
}
