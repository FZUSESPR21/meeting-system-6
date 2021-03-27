package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ForumsDao;
import dao.UserForumDao;
import domain.Forums;
import domain.User;
import domain.UserForum;

/**
 * Servlet implementation class ForumListServlet
 */
@WebServlet("/ForumList")
public class ForumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		ForumsDao forumsDao = new ForumsDao();
		List<Forums> forums = forumsDao.getAllForums();
		User user = (User)session.getAttribute("user");
		UserForumDao ufdao = new UserForumDao();
		List<Forums> selectedForums = ufdao.searchForums(user.getUsername());
		
		PrintWriter out= response.getWriter();
		out.write("[");
		for (int i = 0; i < forums.size(); i++)
		{
			boolean isSelected = false;
			for (int j = 0; j < selectedForums.size(); i++)
				if (selectedForums.get(j).getForumnama().equals(forums.get(i).getForumnama()))
				{
					isSelected = true;
					break;
				}
			if (i == forums.size() - 1)
			{
				out.write("{\"forumname\": \"" + forums.get(i).getForumnama() + "\",");
				out.write("\"selected\": "+ isSelected+"}");
			}
			else {
				out.write("{\"forumname\": \"" + forums.get(i).getForumnama() + "\",");
				out.write("\"selected\": "+ isSelected+"},");
			}
		}
		out.write("]");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
