package cn.yogehaoren.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;

/**
 * @author WangNing
 * @version 1.0
 * @date 2021/2/17 13:02
 */
public class MailUtils {

    private static final String password = "*";
    private static final String host = "*";
    private static final String email = "*";

    public static void sendMessage(String message) throws Exception {
        // 创建简单邮件对象
        SimpleEmail mail = new SimpleEmail();
        // 开启DeBug模式
        mail.setDebug(false);
        //启用ssl加密
        mail.setSSLOnConnect(true);
//使用465端口(不设置也可，ssl默认为465)
        mail.setSslSmtpPort("465");
        // 设置服务器地址
        mail.setHostName(host);
        // 设置发送者邮箱地址及密码[认证发送邮件是否正确]
        mail.setAuthentication(email, password);
        // 设置编码格式
        mail.setCharset("UTF-8");
        // 设置发送者
        mail.setFrom(email);
        // 接受邮箱帐号
        mail.addTo(email);
        // 设置主题
        mail.setSubject("疫情通每日汇报");
        // 设置正文
        mail.setMsg(message);
        // 发送
        mail.send();
    }



}
