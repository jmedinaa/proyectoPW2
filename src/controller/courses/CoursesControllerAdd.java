package controller.courses;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;

import javax.jdo.PersistenceManager;
import model.entity.*;

@SuppressWarnings("serial")
public class CoursesControllerAdd extends HttpServlet {
	

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
	User currentUser = UserServiceFactory.getUserService().getCurrentUser();
	PersistenceManager pm =PMF.get().getPersistenceManager();
	
	if(currentUser==null){
		RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
		dip.forward(req, resp);
	}
	else{
		//usuario maestro
		if(currentUser.getEmail().equals("jmedinaa@unsa.edu.pe")){
			String query5 = "select from " + model.entity.User.class.getName() + " where nameRole == 'profesor'";
			List<model.entity.User> teachers = (List<model.entity.User>)pm.newQuery(query5).execute();
		
			req.setAttribute("teachers", teachers);
			try {
				req.getRequestDispatcher("/WEB-INF/Views/Courses/add.jsp").forward(req, resp);
				
			
			} catch (ServletException e) {
				
				e.printStackTrace();
			}
			finally {
				pm.close();
			}
		}
		else{
			
			String query = "select from "+ model.entity.User.class.getName()+
					" where email=='"+currentUser.getEmail()+"'"+
					" && status==true";
			List<model.entity.User>uSearch=(List<model.entity.User>)pm.newQuery(query).execute();
			if(uSearch.isEmpty()){
				RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
				dip.forward(req, resp);
			}
			else{
				System.out.println(req.getServletPath());
				
				String query2 = "select from " + Resource.class.getName()+
						" where url=='"+req.getServletPath()+"'"+
						" && status==true";
				List<Resource>rSearch=(List<Resource>)pm.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
					dip.forward(req, resp);
				}
				else{
					String query3 = "select from "+Access.class.getName()+
							" where idRole=="+uSearch.get(0).getIdRole()+
							" && idResourse=="+rSearch.get(0).getId()+
							" && status==true";
					List<Access>aSearch=(List<Access>)pm.newQuery(query3).execute();
					if(aSearch.isEmpty()){
						RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
						dip.forward(req, resp);
					}
					else{
						
						

						String query5 = "select from " + model.entity.User.class.getName() + " where nameRole == 'profesor'";
						List<model.entity.User> teachers = (List<model.entity.User>)pm.newQuery(query5).execute();
					
						req.setAttribute("teachers", teachers);
						try {
							req.getRequestDispatcher("/WEB-INF/Views/Courses/add.jsp").forward(req, resp);
							
						
						} catch (ServletException e) {
							
							e.printStackTrace();
						}
						finally {
							pm.close();
						}
		
					}
					
				}
			}
		}
	}
}
		

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	
	String name = req.getParameter("name");
	//idUser +emailUser
	String idEmailUser = req.getParameter("idTeacher");
	//separacion
	Long idUser=Long.parseLong(idEmailUser.substring(0,16));
	String emailUser=idEmailUser.substring(16,idEmailUser.length());
	
	
	model.entity.Course course = new model.entity.Course(name ,idUser,emailUser);
	PersistenceManager pm = PMF.get().getPersistenceManager();
	try{
		pm.makePersistent(course);
		}
	finally{
		pm.close();
	}
		resp.sendRedirect("/courses");
}
	
	
}
