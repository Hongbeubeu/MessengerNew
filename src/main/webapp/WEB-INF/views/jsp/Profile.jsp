<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>${user.lastName} ${user.firstName} </title>
	<link href="<c:url value="/resources/css/styleprofile.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/font/css/all.css" />" rel="stylesheet">
</head>
<body>
	<c:url value="/update" var="update"/>	
	<c:url value="/chat" var="chat"/>
	<c:url value="/logout" var="logout"/>
	<div class="container emp-profile">
		<div class="row"> 
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<div class="profile-img">
		            <img class= "profile-image" src="<c:url value="/resources/image/avatar.jpg"/>">
		     	</div>
			</div>
			<div class="col-md-2">
			
				<form:form action="${update}" method="POST" modelAttribute="user">
			   		<button class="profile-edit-btn" name="btnAddMore">Edit Profile</button>
			   		<input type="hidden" name  = "id" value = "<%= session.getAttribute("id") %>"/>
			   	</form:form>
			   	<form:form action="${chat}" method="POST">
			   		<button class="messenger-btn" name="btnAddMore" >Messenger</button>
			   		<input type="hidden" name  = "userId" value = "<%= session.getAttribute("id") %>"/>
			   	</form:form>
			   	<form:form action="${logout}" method="POST">
			   		<button class="logout-btn" name="btnAddMore" >Logout</button>
			   	</form:form>
			
			</div> 
		 </div>		 
		 <div class="row">
		   	<div class="col-md-4">
		   	</div>
		   	<div class="col-md-4">
		   		<div class="profile-head">
		        	<h2 align="center">${user.lastName} ${user.firstName}</h2>
		        	<div class="row">
		        	</div>
		            <ul class="nav nav-tabs" id="myTab" role="tablist">
		           		<li class="nav-item">
		                	<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Profile</a>
		              	</li>
		          	</ul>
		          	<div class="tab-content profile-tab" id="myTabContent">
		       			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
			          
			                <div class="row">
			                  	<div class="col-md-6">
			                      	<label>First Name</label>
			                   	</div>
			                    <div class="col-md-6">
			                    	<p>${user.firstName}</p>
			                    </div>
			               	</div>
			               	<div class="row">
			                  	<div class="col-md-6">
			                      	<label>Last Name</label>
			                   	</div>
			                    <div class="col-md-6">
			                    	<p>${user.lastName}</p>
			                    </div>
			               	</div>
		                    <div class="row">
		                        <div class="col-md-6">
		                            <label>Email</label>
		                        </div>
		                        <div class="col-md-6">
		                            <p>${user.email}</p>
		                        </div>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-6">
		                            <label>Phone</label>
		                        </div>
		                        <div class="col-md-6">
		                            <p>${user.phoneNumber}</p>
		                        </div>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-6">
		                            <label>Gender</label>
		                        </div>
		                        <div class="col-md-6">
		                            <p>${user.gender}</p>
		                        </div>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-6">
		                            <label>Address</label>
		                        </div>
		                        <div class="col-md-6">
		                            <p>${user.address}</p>
		                        </div>
		                    </div>
			          	</div>
		       		</div>
		    	</div>
			</div>
		      
		</div>
	</div>
</body>
</html>