package pers.xianRong.web.entity;

import org.springframework.data.domain.Page;

/**
 * Created by user on 2017/2/10.
 */
public class ArticleListDto {

    private Page<Article> list;

    private long count;

    public Page<Article> getList() {
        return list;
    }

    public void setList(Page<Article> list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
