package com.wn.common.util;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class EmailUtil {
	/**
     * 发送邮件
     * @param user 发件人邮箱
     * @param password 授权码（注意不是邮箱登录密码）dqqyvyactvhyijjc
     * @param host smtp.163.com smtp.qq.com
     * @param from 发件人邮箱
     * @param to 接收者邮箱 
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return success 发送成功 failure 发送失败
     * @throws Exception
     */
    public static String sendMail(String user, String password, String host,
            String from, String to, String subject, String content)
            throws Exception {
        if (to != null){
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            Authenticator authenticator = new Authenticator(){
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user,password);
                }
            };
            Session session = Session.getInstance(props, authenticator);
            session.setDebug(true);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                if (!to.trim().equals(""))
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(to.trim()));
                message.setSubject(subject);
                MimeBodyPart mbp1 = new MimeBodyPart(); // 正文
                mbp1.setContent(content, "text/html;charset=utf-8");
                Multipart mp = new MimeMultipart(); // 整个邮件：正文+附件
                mp.addBodyPart(mbp1);
                // mp.addBodyPart(mbp2);
                message.setContent(mp);
                message.setSentDate(new Date());
                message.saveChanges();
                Transport.send(message);
            } catch (Exception e){
                e.printStackTrace();
                return "failure";
            }
            return "success";
        }else{            
            return "failure";
        }
    }
}
