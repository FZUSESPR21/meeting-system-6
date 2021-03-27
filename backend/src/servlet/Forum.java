package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import dao.ForumsDao;
import dao.UserDao;
import domain.Forums;
import domain.User;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/Forum")
public class Forum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forum() {
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
		//JSONObject jo = JSONObject.parseObject("{\"forumname\":\"bb\"}");
		
		String forumname = jo.getString("forumname");
		ForumsDao forumsDao = new ForumsDao();
		Forums forums = forumsDao.findForums(forumname);

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println(JSONObject.toJSONString(forums));
		out.write(JSONObject.toJSONString(forums));
	}

}
