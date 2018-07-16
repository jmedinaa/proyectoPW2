package controller.citations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class CitationsControllerEdit extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		User currentUser = UserServiceFactory.getUserService().getCurrentUser();
		
		if(currentUser==null){
			RequestDispatcher dip =getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp");
			dip.forward(req, resp);
		}
		else{
			PersistenceManager pm =PMF.get().getPersistenceManager();
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
						
						PersistenceManager pm2 = PMF.get().getPersistenceManager();
						
						try {
							
							String id = req.getParameter("id");
							Long idLong = Long.parseLong(id); 
							Citation citation = pm2.getObjectById(Citation.class,idLong);
							req.setAttribute("citation",citation);
							
							
							req.getRequestDispatcher("/WEB-INF/Views/Citations/edit.jsp").forward(req, resp);
							
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
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
			
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		String date = req.getParameter("date");
		String description = req.getParameter("description");
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			Citation citation = pm.getObjectById(Citation.class,idLong);
			citation.setName(name);
			citation.setType(type);
			citation.setDate(ParseFecha(date));
			citation.setDescription(description);	
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
			resp.sendRedirect("/citations");
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