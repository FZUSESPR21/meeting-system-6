package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import dao.MessagesDao;
import domain.Message;

/**
 * Servlet implementation class AddMessage
 */
@WebServlet("/AddMessage")
public class AddMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req = request.getReader().readLine();
		//JSONObject jo = JSONObject.parseObject(req);
		JSONObject jo = JSONObject.parseObject("{\"message\":\"1\",\"forumname\":\"3\"}");
		Message message = new Message(-1,jo.getString("message"),jo.getString("forumname"));
		MessagesDao messagesDao = new MessagesDao();
		boolean isSuccess = messagesDao.addMessage(message);
		PrintWriter out = response.getWriter();
		if (isSuccess)	out.write("{\"result\":\"success\"}");
		else out.write("{\"result\":\"fail\"}");
		
	}

}
