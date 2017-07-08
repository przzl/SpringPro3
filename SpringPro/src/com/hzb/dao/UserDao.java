package com.hzb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hzb.domain.User;

@Repository
//使用 @Repository 定义一个dao的bean
public class UserDao {
    @Autowired
    //使用  @Autowired 将Spring容器中的bean JdbcTemplate注入进来
    private JdbcTemplate jdbcTemplate;
    @Autowired
    //将 HibernateTemplate 注入进来
    private HibernateTemplate hibernateTemplate;
    //通过用户名和密码查询用户的个数
    public int getMatchCount(String userName, String password) {
        String sqlStr = " SELECT count(*) FROM t_user "
                + " WHERE user_name =? and password=? ";
        return jdbcTemplate.queryForInt(sqlStr, new Object[]{userName, password});
    }
    //保存，这里不能用hibernateTemplate.save方法，该方法会将对象作为一条新的记录保存。
    public void save(User user) {
        hibernateTemplate.saveOrUpdate(user);
    }
    //通过用户名查询用户
    public User findUserByUserName(final String userName) {
        String sqlStr = " SELECT user_id,user_name "
                + " FROM t_user WHERE user_name =? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                    }
                });
        return user;
    }
    
    //通过用户名查询用户
    public List<User> findUserByName(String userName){
        return (List<User>)hibernateTemplate.find("from User u where u.userName =?",userName) ;
    }
}
