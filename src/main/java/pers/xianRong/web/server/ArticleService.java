package pers.xianRong.web.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pers.xianRong.web.Dao.ArticleDao;
import pers.xianRong.web.entity.Article;
import pers.xianRong.web.entity.ArticleListDto;

/**
 * Created by user on 2016/12/20.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    /*
    * 0表示成功，1表示失败
    * */
    public int  saveArticle(Article article){
        Article save = articleDao.save(article);
        if(save!=null){
           return 0;
        }else{
            return 1;
        }
    }

    public ArticleListDto findByPage(int page,int size){
        ArticleListDto dto = new ArticleListDto();
        PageRequest request=this.buildPageRequest(page,size);
        Page<Article> all = articleDao.findAll(request);
        long count= articleDao.count();
        dto.setList(all);
        dto.setCount(count);
        return dto;
    }

    public Article findOne(Integer id){
        return articleDao.findOne(id);
    }

    //构建PageRequest
    private PageRequest buildPageRequest(int pageNumber, int pagzSize) {

        return new PageRequest(pageNumber - 1, pagzSize, new Sort(Sort.Direction.DESC,"createTime"));
    }
}
