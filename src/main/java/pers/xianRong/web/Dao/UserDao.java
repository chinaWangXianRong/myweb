package pers.xianRong.web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pers.xianRong.web.entity.User;

/**
 * Created by user on 2016/12/14.
 */
@Transactional
public interface UserDao extends JpaRepository<User,Integer> {
    public User findUserByEmail(String email);
    public User findUserByEmailAndPassword(String email,String password);
}
