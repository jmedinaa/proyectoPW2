<%@page import="model.entity.Citation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.entity.Role" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Proyecto Final</title>
	<meta charset="utf-8">
	<meta name ="viewport" content="width-divice-width, initial-scale=1, shrink-to-fit-on">
	<meta http-equiv="x-ua-compatible" content="ie-edge">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	 <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    .row.content {
      padding-top: 10px;
      height: 450px
     }
   
    .sidenav {
      padding-top: 10px;
      background-color: #f1f1f1;
      height: 1100px;
    }
   
    </style>
</head>

<body>
	<nav class="navbar navbar-light navbar-expand-md fixed-top" style="background-color: #e3f2fd;">
		<div class="container-fluid">
	  		<a class="navbar-brand" href="/index.html">
			    <img src="/img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="logo Sistema Academico">
			    Sistema Academico
		    </a>
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="	false" aria-label="Toggle navigation">
		   		  <span class="navbar-toggler-icon"></span>
		    </button>
			<div class="collapse navbar-collapse " id="navbarTogglerDemo01"> 
			    <div class="navbar-nav mr-auto  text-center">
				    <a class="nav-item nav-link" href="/courses">Courses</a>
				    <a class="nav-item nav-link" href="/notes">Notes</a>
				    <a class="nav-item nav-link" href="/citations">Citations</a>
				    <a class="nav-item nav-link" href="/reports">Reports</a>
			    </div>
			    <div class="dropdown btn " >
					<button class="btn btn-outline dropdown-toggle text-muted" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: #e3f2fd;">
					    Administration
					</button>
					<div class="dropdown-menu text-muted" aria-labelledby="dropdownMenuButton">
					    <a class="dropdown-item text-muted" href="/users">Users</a>
					    <a class="dropdown-item text-muted" href="/roles">Roles</a>
					    <a class="dropdown-item text-muted" href="/resources">Resources</a>

					    <div class="dropdown-divider"></div>
	                    <a class="dropdown-item text-muted" href="/access">Access</a>
                   
					</div>
			    </div>
				<div class="d-flex flex-row justify-content-center">
				     <a href="/users/login" class="btn btn-outline-primary mr-2">login in </a>
				    <a href="/users/logout" class="btn btn-outline-primary">login out</a>
				</div>
			</div>	
		</div>		
	</nav>
	<br>
	<br>
	<div class="container-fluid text-center">    
	  <div class="row content">
	    <div class="col-sm-2 sidenav">
	       <div class="navbar-nav mr-auto  text-left">
	       	        <a class="nav-item nav-link text-muted" >Actions</a>
				    <a class="nav-item nav-link" href="/citations/add">New Citation</a>
			</div>
	    </div>
	    <div class="col-sm-10 text-left"> 
	    <br>
	      <h3 class="text-primary">Citations</h3>
		  <table class="table table-bordered">
		    <thead>
		      <tr>
		        <th>Id</th>
		        <th>Name</th>
		        <th>Type</th>
		        <th>Date</th>
		        <th>Description</th>
		        <th>Date Created</th>
		        <th>Actions</th>
		      </tr>
		    </thead>
		    <tbody>
		      
              <% 
				List<Citation> array = (List<Citation>)request.getAttribute("citations");
			
				if(array.size() > 0) {
					
					for(Citation cit : array){
		      %>
		      <tr>
		        <td><%=cit.getId() %></td>
		        <td><%=cit.getName() %></td>
		        <td><%=cit.getType() %></td>
		        <td><%=cit.getDate() %></td>
		        <td><%=cit.getDescription() %></td>
		        <td><%=cit.getCreated() %></td>
		        <td>
		        <a href="/citations/view?id=<%=cit.getId()%>">View</a>  
		        <a href="/citations/edit?id=<%=cit.getId()%>">Edit</a>  
		        <a href="/citations/delete?id=<%=cit.getId()%>">Delete</a>
		        </td>
		      </tr>
		      	<% } } %>
		    </tbody>
		  </table>
	    </div>
	  </div>
	</div>

	<!-- jQuery library -->
	<script src="js/jquery.min.js"></script>
	<!-- Popper JS -->
	<script src="js/popper.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>