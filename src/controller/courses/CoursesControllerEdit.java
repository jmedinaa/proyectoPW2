package controller.courses;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class CoursesControllerEdit extends HttpServlet{
	
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
			try {
				
				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id); 
				model.entity.Course course = pm.getObjectById(model.entity.Course.class,idLong);
				req.setAttribute("course",course);
				
				//roles 
				Query query4 = pm.newQuery(model.entity.User.class);
				List<model.entity.User> teachers = (List<model.entity.User>)query4.execute("select from User where nameRole == 'profesor'");
				req.setAttribute("teachers", teachers);
				
				req.getRequestDispatcher("/WEB-INF/Views/Courses/edit.jsp").forward(req, resp);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
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
						
						try {
							
							String id = req.getParameter("id");
							Long idLong = Long.parseLong(id); 
							model.entity.Course course = pm.getObjectById(model.entity.Course.class,idLong);
							req.setAttribute("course",course);
							
							//roles 
							Query query4 = pm.newQuery(User.class);
							List<User> teachers = (List<User>)query4.execute("select from User where nameRole == 'profesor'");
							req.setAttribute("teachers", teachers);
							
							req.getRequestDispatcher("/WEB-INF/Views/Courses/edit.jsp").forward(req, resp);
							
							} catch (Exception e) {
								e.printStackTrace();
							}
							finally{
								pm.close();
							}
		
					}
					
				}
			}
		}
	}
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String status = req.getParameter("status");
		String name = req.getParameter("name");	
		String id = req.getParameter("id");
		//idUser + emailUser 
		String idEmailUser = req.getParameter("idTeacher");
		//separacion
		String idUser= idEmailUser.substring(0,16);
		String emailUser= idEmailUser.substring(16,idEmailUser.length());
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			Course course = pm.getObjectById(model.entity.Course.class,idLong);

			course.setName(name);
			course.setIdTeacher(Long.parseLong(idUser));
			course.setEmailTeacher(emailUser);
			course.setStatus(new Boolean (status));
			
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
			resp.sendRedirect("/courses");
	}
	

}


