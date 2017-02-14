package pers.xianRong.web.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xianRong.web.Dao.UserDao;
import pers.xianRong.web.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2016/12/14.
 */
@Service
public class UserServer {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MailServer mailServer;



    /*
    * 注册
    * 0表示成功，1表示失败
    *
    * */
    public int register(User user){
        int result=1;
        try{

            if(user!=null && user.getEmail()!=null && user.getPassword()!=null){
                User u = userDao.findUserByEmail(user.getEmail());
                if(u==null){
                    userDao.save(user);
                    result=0;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;

    }
    /*
    * 登录
    * 0表示成功，1表示失败
    *
    * */
    public int login(HttpServletRequest request,User user){
        int result=1;
        try{

            if(user!=null && user.getEmail()!=null && user.getPassword()!=null){
                User u =   userDao.findUserByEmailAndPassword(user.getEmail(),user.getPassword());
                if(u!=null){
                   request.getSession().setAttribute("user",u);
                    result=0;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;

    }
    /*
* 退出
* 0表示成功，1表示失败
*
* */
    public int quit(HttpServletRequest request){
        int result=1;
        try{
            request.getSession().removeAttribute("user");
            result=0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public User getUserByEmail(String email){
        return userDao.findUserByEmail(email);
    }
}

