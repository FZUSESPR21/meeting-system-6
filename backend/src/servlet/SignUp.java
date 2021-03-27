package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getReader().readLine();
        UserDao userDao = new UserDao();
        JSONObject jo = JSONObject.parseObject(str);
        User user = new User();
        
        String p1 = jo.getString("password1");
        String p2 = jo.getString("password2");
        String username = jo.getString("username");
        boolean isSuccess = false;
        	if (p1.equals(p2))
        	isSuccess = true;
        System.out.println(isSuccess);
        user.setPassword(jo.getString("password1"));
        user.setIdentity(null);
        user.setUsername(username);
        
        
        PrintWriter out = null;
        System.out.println(str);
        out = resp.getWriter();
        //isSuccess = userDao.addUser(user);
        if(isSuccess)
        {
        	userDao.addUser(user);
            out.write("{\"result\":\"success\"}");
        }
        else out.write("{\"result\":\"fail\"}");
    }

}