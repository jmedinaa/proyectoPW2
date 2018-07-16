<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.entity.Report" %>
<%
Report report = (Report)request.getAttribute("report");
%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Proyecto Final</title>
	<meta charset="utf-8">
	<meta name ="viewport" content="width-divice-width, initial-scale=1, shrink-to-fit-on">
	<meta http-equiv="x-ua-compatible" content="ie-edge">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
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
				    <a class="nav-item nav-link" href="/reports">List Reports</a>
			</div>
	    </div>
	    <div class="col-sm-10 text-left"> 
	    <br>
	      <h3 class="text-primary">Edit Report</h3>
		  <form method="post"  action="/reports/edit">
		   <div class="form-group">
			   <label>Id:</label>
			   <input type="text"  class="form-control" readonly name="id" value= "<%=report.getId()%>">
		   </div>
		   <div class="form-group">
			    <label>Issue:</label>
			    <input type="text" name="issue" class="form-control" placeholder="issue" value="<%=report.getIssue()%>" maxlength="20" required autofocus>
		   </div>
		   <div class="form-group">
			    <label>Name Sender:</label>
			    <input type="text" name="nameSender" class="form-control" placeholder="Name Sender" value="<%=report.getNameSender()%>" maxlength="20" required autofocus>
		   </div>
		   <div class="form-group">
			    <label >Description:</label>
			    <textarea class="form-control" name="description" rows="7" placeholder="Description" required><%=report.getDescription()%></textarea>
		   </div>
		   <button type="submit" class="btn btn-primary">Submit</button>
		  </form>
	    </div>
	  </div>
	</div>

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>