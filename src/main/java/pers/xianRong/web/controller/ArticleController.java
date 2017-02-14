package pers.xianRong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xianRong.web.entity.Article;
import pers.xianRong.web.entity.User;
import pers.xianRong.web.server.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2016/12/15.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {


    @Value("${user.picture.path}")
    private String picturePath;
    private String Ext_Name = "gif,jpg,jpeg,png,bmp,swf";
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/publish.html")
    public String goPublishPage(){
        return "publish";
    }

    @RequestMapping("/publish.do")
    public @ResponseBody  String publish(HttpServletResponse response, HttpServletRequest  request, String title, String text,String description){
        String result="发布失败";
        if(request.getSession().getAttribute("user")!=null){
           User user= (User)(request.getSession().getAttribute("user"));
            Article article= new Article();
            article.setText(text);
            article.setTitle(title);
            article.setDescription(description);
            article.setUserId(user.getId());
            int c=articleService.saveArticle(article);
            if(c==0){
                result ="发布成功！";
            }
        }
        return result;
    }

    @RequestMapping("/details/{id}.html")
    public String details(HttpServletResponse response, HttpServletRequest request, @PathVariable(value = "id",required = false) Integer id, Model model){
        Article article=articleService.findOne(id);
        model.addAttribute("article",article);
        return "articleDetails";
    }

}
