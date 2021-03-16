package cn.yogehaoren.utils;

import cn.yogehaoren.model.Config;
import cn.yogehaoren.model.SubmitMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @author WangNing yogehaoren@gamil.com <br>
 * @since 1.0
 */

public class ConfigUtils {




    public static Config loadConfig(String configPath) throws Exception {


        Config config =  new Config();
        Yaml yaml = new Yaml();
        File configFile = new File(configPath);
        if(!configFile.exists()){
            throw new RuntimeException("配置文件不存在");
        }
        FileInputStream configInputStream = new FileInputStream(configPath);
        Map<String, Object> configMap = yaml.load(configInputStream);
        String studentId = (String)configMap.get("studentId");
        config.setStudentId(studentId);
        config.setPassword((String)configMap.get("password"));
        Map<String, Object> messageMap = (Map<String, Object>) configMap.get("message");
        SubmitMessage submitMessage = SubmitMessage.getInstanceFromMap(messageMap);
        config.setSubmitMessage(submitMessage);
        return config;
    }

}
