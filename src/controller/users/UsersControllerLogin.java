package controller.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.User;



@SuppressWarnings("serial")
public class UsersControllerLogin extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		UserService us= UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if(user==null){
			resp.sendRedirect(us.createLoginURL("/users/login"));
		}
		else{
			req.setAttribute("user", user);
			RequestDispatcher dip = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/login.jsp");
			dip.forward(req,resp);
		}
		
	}

}
