package pers.xianRong.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeUtility;

/**
 *发送电子邮件
 * 学习来源：http://www.tuicool.com/articles/JNBJF3a; http://blog.csdn.net/mengxianhua/article/details/7406960
 */
@Component
public class SendMailUtil {

    @Value(value = "${mail.fromName}")
    private String fromName;
    @Value(value ="${mail.fromId}")
    private String fromId;
    @Autowired
    private JavaMailSender mailSender;
    /**
     *发送普通电子邮件：普通的文字，不带附件，不带样式
     * toName 收件人的姓名，toId收件人的邮箱地址，suj主题,text正文内容
     * 返回结果：1表示失败，0表示成功
     */
    public int sendSimpleOneMail(String toName,String toId,String suj,String text){
        try{
            SimpleMailMessage smm= new SimpleMailMessage();
            fromName= MimeUtility.encodeText(fromName);
            toName=MimeUtility.encodeText(toName);
            smm.setFrom(fromName+"<"+fromId+">");
            smm.setTo(toName+"<"+toId+">");
            smm.setSubject(suj);
            smm.setText(text);
            mailSender.send(smm);
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
