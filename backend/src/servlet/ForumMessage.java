package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.MessagesDao;
import domain.Message;

/**
 * Servlet implementation class ForumMessage
 */
@WebServlet("/ForumMessage")
public class ForumMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumMessage() {
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
		JSONObject jo = JSONObject.parseObject("{\"forumname\":\"aa\"}");
		String forumnane = jo.getString("forumname");
		
		MessagesDao messageDao = new MessagesDao();
		ArrayList<Message> messages = messageDao.getMessages(forumnane);
		
		response.setCharacterEncoding("utf-8");
		String json=JSON.toJSONString(messages);
		System.out.println(json);
		response.getWriter().write(json);;
		
	}

}
