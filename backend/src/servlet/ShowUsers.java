package servlet;

import com.alibaba.fastjson.JSON;

import dao.UserForumDao;
import domain.Forums;
import domain.Message;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

public class ShowUsers  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        User user = (User) req.getSession().getAttribute("user");   //改一改这句，从服务器端获得分会名称
        UserForumDao userforumdao = new UserForumDao();
        Forums forums =  userforumdao.searchForum(user.getUsername());
        //下面是将选了该分会用户数转变为jaso语句传递给客户端
        UserForumDao userforumdao1 = new UserForumDao();
        List<User> users= userforumdao1.searchUsers(forums.getForumnama());
        PrintWriter out = null;
        out = resp.getWriter();
        out.write("{\"num\":\""+users.size()+"\",");
        int i=0;
        out.write("uses:[");
        for(User user1 : users)
        {
            if(i==users.size())
            {
                out.write("\""+user1.getUsername()+"\"");
            }
            else
                out.write("\""+user1.getUsername()+"\",");   //将用户信息转变为JSON传递给客户端
            i++;
        }
        out.write("]}");
    }
}