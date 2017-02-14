package pers.xianRong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xianRong.web.Dao.UserDao;
import pers.xianRong.web.entity.User;

/**
 * Created by user on 2016/12/12.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

   /* @Autowired
    private AddressDao addressDao;*/
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/one/{id}.do")
    public @ResponseBody int getOneAddress(@PathVariable("id") Integer id){
       // Address a= addressDao.findAddressById(id);
        User user= new User();
        user.setNickName("weeerere");
        userDao.save(user);
       // System.out.println(a.getName());
        return 1;
    }
}
