package com.hzb.service;

import com.hzb.dao.UserDao;
import com.hzb.domain.User;
import com.hzb.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
//使用 @Service 注解将 UserService 标注成为一个服务层的类
public class UserService {
    @Autowired
    //注入UserDao
    private UserDao userDao;

    //查询匹配的用户
    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    //通过用户名查询用户
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    /**
     * 保存方法
     *
     * @param user
     */
    public void save(User user) {
        userDao.save(user);
    }

    //通过用户名查询用户
    public List<User> findUserByName(String userName) {
        /*List<User> listUser = userDao.findUserByName(userName);
        save(listUser.get(0));*/
        return userDao.findUserByName(userName);
    }


    /**
     * 根据坐标查询地址
     *
     * @param
     * @return
     * @throws IOException
     */
    public String GetAddr(String latitude, String longitude) throws IOException {

        String data = "";
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?ak=iC9ZOx2tpBpuorFRtqPYbbkBhzqC2CH7&location=%s&output=json&pois=1", latitude + "," + longitude);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                data = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (insr != null) {
                insr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        
        data =JsonUtil.parseJson(data) ;
        return data;
    }


}
