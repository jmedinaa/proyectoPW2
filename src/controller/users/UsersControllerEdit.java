package controller.users;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UsersControllerEdit extends HttpServlet{
	
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
				model.entity.User user = pm.getObjectById(model.entity.User.class,idLong);
				req.setAttribute("user",user);
				
				//roles 
				Query query4 = pm.newQuery(Role.class);
				List<Role> roles = (List<Role>)query4.execute("select from Role");
				req.setAttribute("roles", roles);
				
				req.getRequestDispatcher("/WEB-INF/Views/Users/edit.jsp").forward(req, resp);
				
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
							model.entity.User user = pm.getObjectById(model.entity.User.class,idLong);
							req.setAttribute("user",user);
							
							//roles 
							Query query4 = pm.newQuery(Role.class);
							List<Role> roles = (List<Role>)query4.execute("select from Role");
							req.setAttribute("roles", roles);
							
							req.getRequestDispatcher("/WEB-INF/Views/Users/edit.jsp").forward(req, resp);
							
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
		
			
		String id = req.getParameter("id");
		//idRole + nameRole 
		String idNameRole = req.getParameter("idRole");
		//separacion
		String idRole= idNameRole.substring(0,16);
		String nameRole= idNameRole.substring(16,idNameRole.length());
		
		
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		String gender = req.getParameter("gender");
		String status = req.getParameter("status");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			model.entity.User user = pm.getObjectById(model.entity.User.class,idLong);
			user.setIdRole(Long.parseLong(idRole));
			user.setNameRole(nameRole);
			user.setEmail(email);
			user.setBirth(ParseFecha(birth));
			user.setGender(new Boolean(gender));
			user.setStatus(new Boolean(status));
			
			
			
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
			resp.sendRedirect("/users");
	}
	
	public static Date ParseFecha(String fecha){
	     SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
	     Date fechaDate = null;
	     try {
	         fechaDate = formato.parse(fecha);
	     } 
	     catch (ParseException ex) 
	     {
	         System.out.println(ex);
	     }
	     return fechaDate;
	 }
}


