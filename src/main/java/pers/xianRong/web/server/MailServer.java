package pers.xianRong.web.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.xianRong.web.Dao.UserDao;
import pers.xianRong.web.entity.User;
import pers.xianRong.web.util.SendMailUtil;
import pers.xianRong.web.util.VerificationCodeUtil;

/**
 * Created by user on 2016/12/14.
 */
@Service
//@CacheConfig(cacheNames = "verification")
public class MailServer {

    @Autowired
    private VerificationCodeUtil verificationCodeUtil;
    @Autowired
    private UserDao userDao;

    @Autowired
    private SendMailUtil util;
    @Value("${mail.toName.default}")
    private String defaultToName;
    @Value("${mail.register.subject}")
    private String registerSuject;
    @Value("${mail.register.text}")
    private String registertext;


    @Value("${mail.findPwd.subject}")
    private String findPwdSuject;
    @Value("${mail.findPwd.text}")
    private String findPwdText;





    public String getEmailCode(String email){

        return  verificationCodeUtil.getEmailVerificationCode(email);
    }


    /*
    *email 表示要发送的邮箱地址 必填
    * nickName表示要发送人的姓名 可填
    * type表示发送的类型，0表示注册发送验证码，1表示找回密码发送验证码 必填
    * 返回值：0表示成功，-1表示异常失败，1表示email在注册曾在失败和找回密码不曾在地址失败
    */
    public int sendMail(String email,String nickName,int type){
        int result=-1;
        try{
            User user = userDao.findUserByEmail(email);
            if(type==0){
                if (user!=null){
                    result=1;
                }else{
                    result=util.sendSimpleOneMail(nickName,email,registerSuject,registertext+ verificationCodeUtil.makeVerificationCode(email));
                }

            }else if(type==1){
                if(user==null){
                    result=1;
                }else{
                    result=util.sendSimpleOneMail(user.getNickName(),email,findPwdSuject,user.getNickName()+findPwdText+ user.getPassword());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
