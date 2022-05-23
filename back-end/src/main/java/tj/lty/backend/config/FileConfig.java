package tj.lty.backend.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tj.lty.backend.model.FileProperty;

@Configuration
@EnableConfigurationProperties(FileProperty.class)
public class FileConfig {
    @Bean
    @Primary
    public FileProperty getConfig(){
        return new FileProperty();
    }
}
