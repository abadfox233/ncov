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
        RequestBody requestBody = FormBody.create("szgjcs=&szcs=&szgj=&zgfxdq=0&mjry=0&csmjry=0&tw=3&sfcxtz=0&sfjcbh=0&sfcxzysx=0&qksm=&sfyyjc=0&jcjgqr=0&remark=&address=%E5%AE%89%E5%BE%BD%E7%9C%81%E5%AE%BF%E5%B7%9E%E5%B8%82%E5%9F%87%E6%A1%A5%E5%8C%BA%E8%A5%BF%E5%85%B3%E8%A1%97%E9%81%93%E6%B2%B3%E7%95%94%E5%A3%B9%E5%8F%B7&geo_api_info=%7B%22type%22%3A%22complete%22%2C%22position%22%3A%7B%22Q%22%3A33.644534776476%2C%22R%22%3A116.96928249783002%2C%22lng%22%3A116.969282%2C%22lat%22%3A33.644535%7D%2C%22location_type%22%3A%22html5%22%2C%22message%22%3A%22Get+ipLocation+failed.Get+geolocation+success.Convert+Success.Get+address+success.%22%2C%22accuracy%22%3A21327%2C%22isConverted%22%3Atrue%2C%22status%22%3A1%2C%22addressComponent%22%3A%7B%22citycode%22%3A%220557%22%2C%22adcode%22%3A%22341302%22%2C%22businessAreas%22%3A%5B%5D%2C%22neighborhoodType%22%3A%22%22%2C%22neighborhood%22%3A%22%22%2C%22building%22%3A%22%22%2C%22buildingType%22%3A%22%22%2C%22street%22%3A%22%E9%93%B6%E6%B2%B3%E4%B8%80%E8%B7%AF%22%2C%22streetNumber%22%3A%22555%E5%8F%B7%22%2C%22country%22%3A%22%E4%B8%AD%E5%9B%BD%22%2C%22province%22%3A%22%E5%AE%89%E5%BE%BD%E7%9C%81%22%2C%22city%22%3A%22%E5%AE%BF%E5%B7%9E%E5%B8%82%22%2C%22district%22%3A%22%E5%9F%87%E6%A1%A5%E5%8C%BA%22%2C%22township%22%3A%22%E8%A5%BF%E5%85%B3%E8%A1%97%E9%81%93%22%7D%2C%22formattedAddress%22%3A%22%E5%AE%89%E5%BE%BD%E7%9C%81%E5%AE%BF%E5%B7%9E%E5%B8%82%E5%9F%87%E6%A1%A5%E5%8C%BA%E8%A5%BF%E5%85%B3%E8%A1%97%E9%81%93%E6%B2%B3%E7%95%94%E5%A3%B9%E5%8F%B7%22%2C%22roads%22%3A%5B%5D%2C%22crosses%22%3A%5B%5D%2C%22pois%22%3A%5B%5D%2C%22info%22%3A%22SUCCESS%22%7D&area=%E5%AE%89%E5%BE%BD%E7%9C%81+%E5%AE%BF%E5%B7%9E%E5%B8%82+%E5%9F%87%E6%A1%A5%E5%8C%BA&province=%E5%AE%89%E5%BE%BD%E7%9C%81&city=%E5%AE%BF%E5%B7%9E%E5%B8%82&sfzx=0&sfjcwhry=0&sfjchbry=0&sfcyglq=0&gllx=&glksrq=&jcbhlx=&jcbhrq=&bztcyy=1&sftjhb=0&sftjwh=0&sfjcjwry=0&jcjg=&date=20210216&uid=351265&created=1613458088&jcqzrq=&sfjcqz=&szsqsfybl=0&sfsqhzjkk=0&sqhzjkkys=&sfygtjzzfj=0&gtjzzfjsj=&id=8614586&gwszdd=&sfyqjzgc=&jrsfqzys=&jrsfqzfy=&ismoved=0", parse);


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
