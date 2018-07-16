package controller.access;

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
public class AccessControllerEdit extends HttpServlet{
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
				Access access = pm.getObjectById(Access.class,idLong);
				req.setAttribute("access",access);
				
				//roles 
				Query query4 = pm.newQuery(Role.class);
				List<Role> roles = (List<Role>)query4.execute("select from Role");
				req.setAttribute("roles", roles);
				
				Query query5 = pm.newQuery(Resource.class);
				List<Resource> resources = (List<Resource>)query5.execute("select from Resource");
				req.setAttribute("resources", resources);
				
				req.getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp").forward(req, resp);
				
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
							Access access = pm.getObjectById(Access.class,idLong);
							req.setAttribute("access",access);
							
							//roles 
							Query query4 = pm.newQuery(Role.class);
							List<Role> roles = (List<Role>)query4.execute("select from Role");
							req.setAttribute("roles", roles);
							
							Query query5 = pm.newQuery(Resource.class);
							List<Resource> resources = (List<Resource>)query5.execute("select from Resource");
							req.setAttribute("resources", resources);
							
							req.getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp").forward(req, resp);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						finally{
							pm.close();
						}
		
					}
					
				}
			}}
		}
	}
	
	
	
		
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
				
			String id = req.getParameter("id");
			
//			idRole +nameRole
			String idNameRole = req.getParameter("idRole");
			//separacion
			String idRole= idNameRole.substring(0,16);
			String nameRole=idNameRole.substring(16,idNameRole.length());
			
			// idResource + urlResource
			String idUrlResource = req.getParameter("idResource");
			//separacion
			String idResource = idUrlResource.substring(0,16);
			String urlResource= idUrlResource.substring(16,idUrlResource.length());
			
			String status = req.getParameter("status");
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try{
				Long idLong = Long.parseLong(id);
				
				Access access = pm.getObjectById(Access.class,idLong);
				access.setIdRole(Long.parseLong(idRole));
				access.setNameRole(nameRole);
				access.setIdResourse(Long.parseLong(idResource));
				access.setUrlResource(urlResource);
				access.setStatus(new Boolean(status));
				
				
				
			}
			catch(Exception e){
				System.out.println("Se produjo un Error");
			}
			finally{
				pm.close();
			}
				resp.sendRedirect("/access");
		}
	}



