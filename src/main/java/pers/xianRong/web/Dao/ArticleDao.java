package pers.xianRong.web.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import pers.xianRong.web.entity.Article;

/**
 * Created by user on 2016/12/19.
 */
@Transactional
public interface ArticleDao extends PagingAndSortingRepository<Article,Integer> {
    //public void saveArticle(Article article);

}
