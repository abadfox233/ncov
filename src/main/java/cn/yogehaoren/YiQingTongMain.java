package cn.yogehaoren;

import cn.yogehaoren.utils.HttpUtils;

import java.util.Random;

/**
 * @author WangNing
 * @version 1.0
 * @date 2021/2/17 12:53
 */
public class YiQingTongMain {
    public static void main(String[] args) throws Throwable {

        Random random = new Random();
        Thread.sleep(random.nextInt(1000*60*Integer.parseInt(args[0])));

        HttpUtils.login("*", "*");
        HttpUtils.yiQingTongMessage();
    }
}
