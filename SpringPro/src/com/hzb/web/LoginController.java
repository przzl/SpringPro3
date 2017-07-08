package com.hzb.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.hzb.domain.User;
import com.hzb.service.UserService;


import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
//① 标注成为一个SpringMVC的Controller
@RequestMapping(value = "/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login.html")
    //② 负责处理 /admin/login.html 的请求（需要加上类的映射请求路径）,与index.jsp中的跳转目标保持一致
    public String loginPage() {
        return "login";
    }
    @RequestMapping(value = "/loginCheck.html")
    //② 负责处理 /admin/loginCheck.html 的请求 ，与login.jsp页面form表单的action保持一致
   /* public ModelAndView loginCheck(HttpServletRequest request,HttpServletRequest response) {
        if ("admin".equals(request.getParameter("userName")) && "123456".equals(request.getParameter("password"))) {
            return new ModelAndView("main");
        } else {
            return new ModelAndView("login").addObject("error", "error").addObject("errorMsg", "用户名或密码错误");
        }
    }*/
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidUser =
                userService.hasMatchUser(loginCommand.getUserName(),
                        loginCommand.getPassword());
        System.out.println("LoginController.loginCheck");
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            System.out.println("dad");
            //User user = userService.findUserByUserName(loginCommand.getUserName());
            User user = new User();
            List<User> listUser = userService.findUserByName(loginCommand.getUserName());
            if(listUser!=null &&listUser.size()>0){
                user = listUser.get(0);
            }
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            this.userService.save(user);
            
            return new ModelAndView("main").addObject("user",user);
        }
    }

    @RequestMapping(value = "/getCityName.html")
    protected void getCityName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String longitude = (String) req.getParameter("longitude");
        String latitude = (String) req.getParameter("latitude");
        String cityName = userService.GetAddr(latitude, longitude);
        System.out.println("cityName=" + cityName);
//		ConnDB conn = new ConnDB();
//		String sql = "select * from tb_user";
//		ResultSet rs = conn.executeQuery(sql);
        //PrintWriter out = resp.getWriter();

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        try {
            pw.write(cityName);
            pw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
