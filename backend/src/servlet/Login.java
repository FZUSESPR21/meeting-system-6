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
import dao.UserDaoImpl;
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
		User user = new User();
		user.setUsername(jo.getString("username"));
		user.setPassword(jo.getString("password"));
		user.setIdentity(null);
		UserDao userDao = new UserDaoImpl();
		boolean isUser = userDao.findUser(user);
		//设置session
		request.getSession().setAttribute("username", jo.getString("username"));
		request.getSession().setAttribute("password", jo.getString("password"));
		
		PrintWriter out = response.getWriter();
		if (isUser)	out.write("{\"结果\":\"登录成功\"}");
		else out.write("{\"结果\":\"登录失败\"}");
	}

}
