package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AccessFilter implements Filter {
    public EncodingFilter() {

    }

    public void destroy() {

    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) response;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = req.getServletPath();
        if(path.contains("login.html") || path.contains("signup.html") || path.contains("index.html"))
        {
        	chain.doFilter(req, response);
        }
        else {
			HttpSession session = req.getSession(false);
			if (session == null)
				response.sendRedirect("login.html");
			else {
				User user = (User)session.getAttribute("user");
				if (user == null)
					response.sendRedirect("login.html");
				else {
					if (user.getIdentity().equals("普通用户"))
						response.sendError(403);
					else {
						chain.doFilter(req, response);
					}
				}
			}
		}
    }
}