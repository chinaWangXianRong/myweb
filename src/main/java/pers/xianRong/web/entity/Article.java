package pers.xianRong.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user on 2016/12/19.
 */
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String text;
    @Column(name = "create_time")
    private Date createTime;
    private int status;
    @Column(name = "user_id")
    private Integer userId;
    private String description;

    @OneToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",insertable = false,updatable = false)
    private User user;

    public Article(){
        super();
    }

    public Article(String title, String text, Date createTime, Integer status, Integer userId, User user,String description) {
        this.title = title;
        this.text = text;
        this.createTime = createTime;
        this.status = status;
        this.userId = userId;
        this.user = user;
        this.description=description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
