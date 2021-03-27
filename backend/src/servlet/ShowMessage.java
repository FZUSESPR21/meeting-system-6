package servlet;

import domain.Message;

        import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import dao.MessagesDao;

import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.List;

@WebServlet("/ShowMessage")
public class ShowMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessagesDao messageDao = new MessagesDao();
        List<Message> messages = messageDao.getAllMessages();
        PrintWriter out = null;
        out = resp.getWriter();
        JSONObject.toJSONString(messages);
        out.write("user:[");
        int i=0;
        for(Message message : messages)
        {
            if(i==messages.size())
            {
                out.write("{\"id\":"+message.getId()+",\"message\":"+message.getMessage()+",\"forun\":"+message.getMessage()+"}]");
            }
            else out.write("{\"id\":"+message.getId()+",\"message\":"+message.getMessage()+",\"forun\":"+message.getMessage()+"},");

        }
    }
}


