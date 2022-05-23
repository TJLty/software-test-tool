package tj.lty.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tj.lty.backend.model.FileProperty;

import java.io.File;
import java.util.UUID;

@Service
public class FileUtils {
    @Autowired
    private FileProperty fileProperty;

    public String saveFile(MultipartFile file){
        String os=System.getProperty("os.name");
        String basePath="";
        if (os != null && os.toLowerCase().startsWith("windows")) {
            System.out.printf("当前系统版本是:%s%n", os);
            basePath=fileProperty.getWindows();
        } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
            System.out.printf("当前系统版本是:%s%n", os);
            basePath=fileProperty.getLinux();
        } else { //其它操作系统
            System.out.printf("当前系统版本是:%s%n", os);
        }
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File dest = new File(basePath+ "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
            return basePath+ "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return basePath+ "/" + fileName;
        }
    }
}
