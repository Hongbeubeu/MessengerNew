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
	<c:url value="/processUpdate" var="processUpdate"/>
	<c:url value="/profile" var="profile"/>
	<div class="container emp-profile">
		<div class="row"> 
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<div class="profile-img">
		            <img class= "profile-image" src="<c:url value="/resources/image/${user.avatar }"/>">
		     	</div>
			</div>
			<div class="col-md-2">
				<form:form action="${profile}" method="POST">
			   		<button class="profile-edit-btn" >Profile</button>
			   		<input type="hidden" name="id" value="${cutomer.id }"/>
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
		          	<form:form action="${processUpdate}" method="POST" modelAttribute="user" enctype="multipart/form-data">
			          	<div class="tab-content profile-tab" id="myTabContent">
			       			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
				          
				                <div class="row">
				                  	<div class="col-md-5">
				                      	<label>First Name</label>
				                   	</div>
				                    <div class="col-md-5">
				                    	<form:input path="firstName" class="box" type="text" value="${user.firstName }"/>
				                    </div>
				               	</div>
				               	<div class="row">
				                  	<div class="col-md-5">
				                      	<label>Last Name</label>
				                   	</div>
				                    <div class="col-md-5">
				                    	<form:input path="LastName" class="box" type="text" value="${user.lastName }"/>
				                    </div>
				               	</div>
			                    <div class="row">
			                        <div class="col-md-5">
			                            <label>Email</label>
			                        </div>
			                        <div class="col-md-5">
			                            <form:input path="email" class="box" type="email" value="${user.email }"/>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="col-md-5">
			                            <label>Phone</label>
			                        </div>
			                        <div class="col-md-5">
			                            <form:input path="phoneNumber" class="box" type="text" value="${user.phoneNumber }"/>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="col-md-5">
			                            <label>Gender</label>
			                        </div>
			                        <div class="col-md-5">
			                            <form:input path="gender" class="box" type="text" value="${user.gender }"/>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="col-md-5">
			                            <label>Address</label>
			                        </div>
			                        <div class="col-md-5">
			                            <form:input path="address" class="box" type="text" value="${user.address }"/>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="col-md-5">
			                            <label>Avatar</label>
			                        </div>
			                        <div class="col-md-5 upload-btn-wrapper">
  										<button class="btn_upload">${user.avatar }</button>
  										<form:input path="multipartFile" type="file"/>
									</div>
			                    </div>
			                    <form:input path="avatar" type="hidden" value="${user.avatar }"/>
			                    <button type = "submit" class="profile-edit-btn" name="btnAddMore" >Update</button>
				          	</div>
			       		</div>
		       		</form:form>
		    	</div>
			</div>     
		</div>
	</div>
</body>
</html>