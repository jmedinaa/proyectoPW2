package controller.notes;

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
public class NotesControllerEdit extends HttpServlet{
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
				Note note = pm.getObjectById(Note.class,idLong);
				req.setAttribute("note",note);
				
				
				Query query4 = pm.newQuery(model.entity.User.class);
				List<model.entity.User> students = (List<model.entity.User>)query4.execute("select from User where nameRole == 'alumno'");
				req.setAttribute("students", students);
				
				Query query5 = pm.newQuery(model.entity.Course.class);
				List<Course>courses=(List<Course>)query5.execute("select form Course");
				req.setAttribute("courses", courses);
				
				req.getRequestDispatcher("/WEB-INF/Views/Notes/edit.jsp").forward(req, resp);
				
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
							Note note = pm.getObjectById(Note.class,idLong);
							req.setAttribute("note",note);
							
							
							Query query4 = pm.newQuery(model.entity.User.class);
							List<model.entity.User> students = (List<model.entity.User>)query4.execute("select from User where nameRole == 'alumno'");
							req.setAttribute("students", students);
							
							Query query5 = pm.newQuery(model.entity.Course.class);
							List<Course>courses=(List<Course>)query5.execute("select form Course");
							req.setAttribute("courses", courses);
							
							req.getRequestDispatcher("/WEB-INF/Views/Notes/edit.jsp").forward(req, resp);
							
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
			String idAlum = req.getParameter("idAlum");
			String noteValue = req.getParameter("note");
			
			// idCourse + nameCourse
			String idNameCourse = req.getParameter("idCourse");
			//separacion
			String idCourse = idNameCourse.substring(0,16);
			String nameCourse= idNameCourse.substring(16,idNameCourse.length());
			
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try{
				Long idLong = Long.parseLong(id);
				
				Note note = pm.getObjectById(Note.class,idLong);
				
				note.setIdCourse(Long.parseLong(idCourse));
				note.setIdStudent(Long.parseLong(idAlum));
				note.setNameCourse(nameCourse);
				note.setNoteValue(Double.parseDouble(noteValue));
				
				
				
			}
			catch(Exception e){
				System.out.println("Se produjo un Error");
			}
			finally{
				pm.close();
			}
				resp.sendRedirect("/notes");
		}
	}



