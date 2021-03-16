package cn.yogehaoren;

import cn.yogehaoren.model.Config;
import cn.yogehaoren.utils.ConfigUtils;
import cn.yogehaoren.utils.HttpUtils;

import java.util.Random;

/**
 * @author WangNing yogehaoren@gamil.com <br>
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {

        if(args.length==0){
            System.out.println("请输入配置文件位置");
            System.exit(-1);
        }
        if(args.length == 2){
            if(Integer.parseInt(args[1])>0){
                Random random = new Random();
                Thread.sleep(random.nextInt(1000*60*Integer.parseInt(args[1])));
            }

        }

        Config config = ConfigUtils.loadConfig(args[0]);
        HttpUtils.login(config.getStudentId(), config.getPassword());
        HttpUtils.reportMessage(config.getSubmitMessage());



    }
}
