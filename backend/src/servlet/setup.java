package com.uestc.www.servlets;
import Dao.UserDao;
import Dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.alibaba.fastjson.*;
import domain.User;

//@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getReader().readLine();
        UserDao userDao = new UserDaoImpl();
        JSONObject jo = JSONObject.parseObject(str);
        User user = new User();
        user.setPassword(jo.getString("password"));
        user.setIdentity(jo.getString("identity"));
        user.setUsername(jo.getString("username"));
        PrintWriter out = null;
        out = resp.getWriter();
        if(userDao.addUser(user))
        {
            out.write("{\\\"结果\\\":\\\"注册成功\\\"}");
        }
        else out.write("{\\\"结果\\\":\\\"注册失败\\\"}");
    }

}