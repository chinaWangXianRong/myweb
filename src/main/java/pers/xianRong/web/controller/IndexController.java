package pers.xianRong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.xianRong.web.entity.ArticleListDto;
import pers.xianRong.web.server.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2016/11/25.
 */
@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String root(){
        return "redirect:/index.html";
    }
    @RequestMapping("/index.html")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
       ArticleListDto list= articleService.findByPage(1,20);
        model.addAttribute("articleList",list);
        return "index";
    }
    @RequestMapping("/index/{page}.html")
    public String indexPage(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable("page") Integer page){
       ArticleListDto list= articleService.findByPage(page,20);
        model.addAttribute("articleList",list);
        return "index";
    }
}
