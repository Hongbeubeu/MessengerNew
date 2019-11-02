<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">
	<title>Login</title>
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
           <h5 class="card-title text-center">Sign In</h5>
           <c:url value="/processLogin" var="processLogin"/>
           <c:url value="/register" var="register"/>
           <form:form class="form-signin" action="${processLogin}" method="POST" modelAttribute="user">
             <div class="form-label-group">
             	<form:input path="email" id="inputEmail" type="email" class="form-control" placeholder="Email address" required="required"/>
               	<label for="inputEmail">Email address</label>
             </div>
             <div class="form-label-group">
               <form:input path="password" id="inputPassword" type="password" class="form-control" placeholder="Password" required="required"/>
               <label for="inputPassword">Password</label>
             </div>
             <div class="custom-control custom-checkbox mb-3">
               <input type="checkbox" class="custom-control-input" id="customCheck1">
               <label class="custom-control-label" for="customCheck1">Remember password</label>
               <a href="#">Forgot password!</a>
             </div>
             <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
           </form:form>   
           <hr class="my-4">
           <form:form class="form-signin" action="${register}" method="GET">
           	<button class="btn btn-lg btn-primary btn-block text-uppercase" type ="submit">Register</button>
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