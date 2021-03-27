package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.UserDao;

import domain.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		JSONObject jo = JSONObject.parseObject(req);
		//JSONObject jo = JSONObject.parseObject("{\"username\":\"1\",\"password\":\"3\"}");
		User user = new User();
		user.setUsername(jo.getString("username"));
		user.setPassword(jo.getString("password"));
		user.setIdentity(null);
		UserDao userDao = new UserDao();
		boolean isUser = userDao.findUser(user);
		System.out.println(req);
		request.getSession().setAttribute("user", user);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(isUser);
		if (isUser)	out.write("{\"result\":\"success\"}");
		else out.write("{\"result\":\"fail\"}");
	}

}
