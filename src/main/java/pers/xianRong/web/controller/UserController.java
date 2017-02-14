package pers.xianRong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xianRong.web.entity.User;
import pers.xianRong.web.server.MailServer;
import pers.xianRong.web.server.UserServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2016/12/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;
    @Autowired
    private MailServer mailServer;



    @RequestMapping("/register.html")
    public  String goRisterPgage(){

        return  "register";
    }
    @RequestMapping("/login.html")
    public  String goLoginPgage(){

        return  "login";
    }



    /*
    *发送email
    *1表示失败，0表示成功*/
    @RequestMapping("/sendMail.do")
    public @ResponseBody
    int  sendMail(HttpServletRequest request,
                  HttpServletResponse response, @RequestParam("nickName")String nickName, @RequestParam("email")String email){
                  int result=mailServer.sendMail(email,nickName,0);
        return result;
    }
    /*
    *用户注册
    *1表示失败，0表示成功*/
    @RequestMapping("/register.do")
    @ResponseBody
    public int register(User user){
       int result= userServer.register(user);
        return result;
    }
    /*
    *用户登录
    *1表示失败，0表示成功*/
    @RequestMapping("/login.do")
    @ResponseBody
    public int login(HttpServletRequest request,User user){
       int result= userServer.login(request,user);
        return result;
    }
    /*
    *用户退出
    *1表示失败，0表示成功*/
    @RequestMapping("/quit.do")
    @ResponseBody
    public int quit(HttpServletRequest request){
       int result= userServer.quit(request);
        return result;
    }
    /*
       *是否是注册用户
       *0表示不是已经注册的用户，1表示已经注册过的用户*/
    @RequestMapping("/isRegisterUser.do")
    @ResponseBody
    public int isRegisterEmail(String email){
        int result=0;
        if(userServer.getUserByEmail(email)!=null){
            result=1;
        }

        return result;

    }


    /*
    * 获取指定邮箱的验证码
    * */
    @RequestMapping("/getEmailCode.do")
    @ResponseBody
    public String getEmailCode(String email){
        return mailServer.getEmailCode(email);

    }
    @RequestMapping("/findPwd.html")
    public String goFindPwd(String email){
        return "findPwd";
    }

    /*
    *寻找密码
    * 0表示成功，1表示失败
    * */
    @RequestMapping("/findPwd.do")
    @ResponseBody
    public int findPwd(String email){
        int result=0;
        result= mailServer.sendMail(email,null,1);
        return result;
    }
}
