package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.UserForumDao;
import domain.User;
import domain.UserForum;

/**
 * Servlet implementation class SubscriptionServlet
 */
@WebServlet("/Subscription")
public class SubscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		User user = (User) request.getSession(false);
		JSONObject jo = JSONObject.parseObject(request.getReader().readLine());
		String name = user.getUsername();
		String method = jo.getString("method");
		if (method.equals("subscript"))
		{
			UserForum uf = new UserForum();
			uf.setForumName(jo.getString("forumname"));
			uf.setUserName(name);
			UserForumDao ufd = new UserForumDao();
			ufd.addUserForum(uf);
		}
		else {
			UserForum uf = new UserForum();
			uf.setForumName(jo.getString("forumname"));
			uf.setUserName(name);
			UserForumDao ufd = new UserForumDao();
			ufd.deleteUserForum(uf);
		}
		response.getWriter().write("{\"result\": true}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
