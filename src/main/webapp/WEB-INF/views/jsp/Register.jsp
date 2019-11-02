<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">
	<title>Register</title>
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/font/css/all.css" />" rel="stylesheet">	
</head>
<body>
<div class="container">
   <div class="row">
     <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
       <div class="card card-signin my-5">
         <div class="card-body">
           <h5 class="card-title text-center">Register</h5>
           <c:url value="/login" var="login"/>
           <c:url value="/processRegister" var="processRegister"/>
           <form:form class="form-signin" action="${processRegister}" method="POST" modelAttribute="user">
             <div class="form-label-group">
             	<form:input path="firstName" id="inputFirstName" type="text" class="form-control" placeholder="First Name" required="required"/>
               	<label for="inputFirstName">First Name</label>
             </div>
             <div class="form-label-group">
             	<form:input path="lastName" id="inputLastName" type="text" class="form-control" placeholder="Last Name" required="required"/>
               	<label for="inputLastName">Last Name</label>
             </div>
             <div class="form-label-group">
             	<form:input path="address" id="inputAddress" type="text" class="form-control" placeholder="Address" required="required"/>
               	<label for="inputAddress">Address</label>
             </div>
             <div class="form-label-group">
             	<form:input path="phoneNumber" id="inputPhoneNumber" type="text" class="form-control" placeholder="Phone Number" required="required"/>
               	<label for="inputPhoneNumber">Phone Number</label>
             </div>
             <div class="form-label-group">
             	<form:input path="avatar" id="inputAvatar" type="text" class="form-control" placeholder="avatar" required="required"/>
               	<label for="inputAvatar">Avatar</label>
             </div>
             <div class="form-label-group">
             	<form:input path="gender" id="inputGender" type="text" class="form-control" placeholder="Gender" required="required"/>
               	<label for="inputGender">Gender</label>
             </div>
             <div class="form-label-group">
             	<form:input path="email" id="inputEmail" type="email" class="form-control" placeholder="Email Address" required="required"/>
               	<label for="inputEmail">Email Address</label>
             </div>
             <div class="form-label-group">
               <form:input path="password" id="inputPassword" type="password" class="form-control" placeholder="Password" required="required"/>
               <label for="inputPassword">Password</label>
             </div>
             <div class="form-label-group">
               <form:input path="confirmPassword" id="inputConfirmPassword" type="password" class="form-control" placeholder="Confirm Password" required="required"/>
               <label for="inputConfirmPassword">Confirm Password</label>
             </div>
             <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
           </form:form>
           <hr class="my-4">
           <form:form class="form-signin" action="${login}" method="GET">
           	<button class="btn btn-lg btn-primary btn-block text-uppercase" type ="submit">Sign in</button>
           </form:form>
           <hr class="my-4">
           <button class="btn btn-lg btn-google btn-block text-uppercase" onclick="alert('chức năng này sẽ có sau')" type ="button"><i class="fab fa-google mr-2"></i> Sign in with Google</button>
           <button class="btn btn-lg btn-facebook btn-block text-uppercase" onclick="alert('chức năng này sẽ có sau')" type ="button"><i class="fab fa-facebook-f mr-2"></i>Sign in with Facebook</button>
         </div>
       </div>
     </div>
   </div>
 </div>
</body>
</html>