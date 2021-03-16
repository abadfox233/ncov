package cn.yogehaoren.utils;

import cn.yogehaoren.common.CookieManager;
import cn.yogehaoren.model.SubmitMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author WangNing yogehaoren@gamil.com <br>
 * @since 1.0
 */
public class HttpUtils {

    private static final String LOGIN_URL = "https://xxcapp.xidian.edu.cn/uc/wap/login/check";

    private static final String REPORT_URL = "https://xxcapp.xidian.edu.cn/xisuncov/wap/open-report/save";


    private static final String YI_QING_TONG_REPORT = "https://xxcapp.xidian.edu.cn/ncov/wap/default/save";

    public static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .cookieJar(new CookieManager())
            .build();


    /**
     * 登录
     * @param studentId 学号
     * @param password 密码
     * @throws IOException -
     */
    public static boolean login(String studentId, String password) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", studentId)
                .add("password", password)
                .build();

        Request loginRequest = new Request.Builder().url(LOGIN_URL)
                .post(formBody)
                .header("Origin", "https://xxcapp.xidian.edu.cn")
                .build();

        try(Response loginResponse = httpClient.newCall(loginRequest).execute()){
            if(loginResponse.isSuccessful()){
                JSONObject loginJSON = (JSONObject) JSON.parse(loginResponse.body().string());
                if(loginJSON.getInteger("e") == 0){
                    System.out.println("登录成功");
                    return true;
                }
                return false;

            }

        }

        return false;
    }




    public static void yiQingTongMessage()throws Exception{

        MediaType parse = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody requestBody = FormBody.create("此处填入疫情通编码后的数据", parse);


        Request reportRequest = new Request.Builder()
                .url(YI_QING_TONG_REPORT)
                .post(requestBody)
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Host", "xxcapp.xidian.edu.cn")
                .header("Origin", "https://xxcapp.xidian.edu.cn")
                .header("Referer", "https://xxcapp.xidian.edu.cn/ncov/wap/default/index")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.66")
                .header("X-Requested-With", "XMLHttpRequest")
                .build();

        try(Response reportResponse = httpClient.newCall(reportRequest).execute()){

            if(reportResponse.isSuccessful()){
                String res = reportResponse.body().string();
                MailUtils.sendMessage(res);
                System.out.println("返回信息: "+res);
                JSONObject loginJSON = (JSONObject) JSON.parse(res);
                if(loginJSON.getInteger("e") == 0){
                    System.out.println("上报成功");

                }else if("您已上报过".equals(loginJSON.getString("m"))){
                    System.out.println("已上报");
                }


            }

        }
    }

    /**
     * 上报信息
     * @param message -
     * @throws IOException -
     */
    public static void reportMessage(SubmitMessage message) throws IOException{
        Request reportRequest = new Request.Builder()
                .url(REPORT_URL)
                .post(message.getEncodingData())
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Host", "xxcapp.xidian.edu.cn")
                .header("Origin", "https://xxcapp.xidian.edu.cn")
                .header("Referer", "https://xxcapp.xidian.edu.cn/site/ncov/xidiandailyup")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.66")
                .header("X-Requested-With", "XMLHttpRequest")
                .build();

        try(Response reportResponse = httpClient.newCall(reportRequest).execute()){

            if(reportResponse.isSuccessful()){
                JSONObject loginJSON = (JSONObject) JSON.parse(reportResponse.body().string());
                if(loginJSON.getInteger("e") == 0){
                    System.out.println("上报成功");

                }else if("您已上报过".equals(loginJSON.getString("m"))){
                    System.out.println("已上报");
                }


            }

        }


    }

    /**
     * 获取个人信息
     * @throws IOException -
     */
    public static void getMessage() throws IOException{

        Request reportRequest = new Request.Builder()
                .url("https://xxcapp.xidian.edu.cn/xisuncov/wap/open-report/index")
                .build();
        try(Response response = httpClient.newCall(reportRequest).execute()){
            System.out.println(response.body().string());
        }

    }



}
