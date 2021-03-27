package servlet;

import domain.Message;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

import dao.MessagesDao;

import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.List;

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
        for(Message message : messages)
        {
            out.write("{\"id\":\""+message.getId()+"\"}");
            out.write("{\"message\":\""+message.getMessage()+"\"}");
            out.write("{\"forun\":\""+message.getForum()+"\"}");
        }
    }
}
