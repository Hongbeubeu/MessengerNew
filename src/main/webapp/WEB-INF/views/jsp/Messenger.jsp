<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link href="<c:url value="/resources/css/stylemessenger.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/font/css/all.css" />" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
	<title>Messenger</title>
	<link rel="SHORTCUT ICON" href="<c:url value="/resources/font/css/all.css" />" >
	<script type="text/javascript">
		$(document).ready(function(){
			$('#action_btn').click(function(){
				$('.action_menu').toggle();
			});
		});
	</script>
</head>
<body>	
 	<c:url value="/search" var="search"/>
 	<c:url value="/addFriend" var="addFriend"/>
 	<c:url value="/chatRoom" var="chatRoom"/>
 	<c:url value="/sendMessage" var="sendMessage"/>
	<div class="row">
		<div class="chat col-md-3">
			<div class="card contacts_card " >
				<div class="card-header">
					<form:form action="${search}" method="POST">
						<div class="input-group">
							<input type="email" name="email" placeholder="Search..." value="" class="form-control search" required = "required"/>
							<div class="input-group-prepend">
								<span class="input-group-text search_btn"><i class="fas fa-search" ></i></span>
							</div>
							<input type="hidden" name  = "userId" value = "<%= session.getAttribute("id") %>"/>
						</div>
					</form:form>
				</div>
				<div class="card-body contacts_body">	'
				<ul class="contacts"> 
					<c:if test="${not empty findFriend }">
					<li class="active"  id="action_btn">
						<div class="d-flex bd-highlight">
							<div class="img_cont">
								<img src="<c:url value="/resources/image/${user.avatar }"/>" class="rounded-circle user_img">
								<span class="online_icon"></span>
							</div>
							<div class="user_info">
								<span>${findFriend.lastName} ${findFriend.firstName}</span>
								<p>${findFriend.firstName} is online</p>
							</div>
						</div>
					</li>
					<div class="action_menu">
					<ul>
						<form:form action="${addFriend}" method="POST">
							<button class= "messenger-btn">Contact</button>
				   			<input type="hidden" name="userId" value = "<%= session.getAttribute("id") %>"/>
				   			<input type="hidden" name="friendId" value="${findFriend.id}"/>
			   			</form:form>
			   			<form:form action="${chat}" method="POST">
							<button class= "messenger-btn">Chat</button>
				   			<input type="hidden" name="userId" value = "<%= session.getAttribute("id") %>"/>
				   			<input type="hidden" name="friendId" value="${findFriend.id}"/>
			   			</form:form>
					</ul>
					</div>	
					</c:if>
					<c:forEach var="friend" items="${listFriend}">
						<li class="active">
							<div class="d-flex bd-highlight">
								<div class="img_cont">
									<img src="<c:url value="/resources/image/${friend.avatar }"/>" class="rounded-circle user_img">
									<span class="online_icon"></span>
								</div>
								<div class="user_info">
									<span>${friend.lastName} ${friend.firstName}</span>
									<p>${friend.firstName} is online</p>
									<form:form action="${chatRoom}" method="POST">
										<button class = "messenger-btn-x">chat</button>
										<input type="hidden" name="userId" value = "<%= session.getAttribute("id") %>"/>
				   						<input type="hidden" name="friendId" value="${friend.id}"/>
									</form:form>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
				</div>
				<div class="card-footer"></div>
			</div>
		</div>
		<div class="chat card col-md-9">
			<div class="card-header msg_head">
				<div class="d-flex bd-highlight">
				<c:if test= "${not empty conversation }">
					<div class="img_cont">
						<img src="<c:url value="/resources/image/${user.avatar }"/>" class="rounded-circle user_img">
						<span class="online_icon"></span>
					</div>
					<div class="user_info">
						<span>${conversation.conversationName}</span>
						<p>Active now</p>
					</div>
					<div class="video_cam">
						<span><i class="fas fa-video"></i></span>
						<span><i class="fas fa-phone"></i></span>
					</div>
				</c:if>
				</div>
			</div>
			<div class="card-body msg_card_body">
				<c:if test= "${not empty chatsentence }">
					<c:forEach var="chat" items="${chatsentence}">
						<c:if test = "${chat.userId != user.id }">
							<div class="d-flex justify-content-start mb-4">
								<div class="img_cont_msg">
									<img src="<c:url value="/resources/image/${chat.avatar }"/>" class="rounded-circle user_img_msg">
								</div>
								<div class="msg_cotainer">
									${chat.context}
									<span class="msg_time">${chat.createAt}</span>
								</div>
							</div>
						</c:if>
						<c:if test="${chat.userId == user.id }">
							<div class="d-flex justify-content-end mb-4">
								<div class="msg_cotainer_send">
									${chat.context }
									<span class="msg_time_send">${chat.createAt}</span>
								</div>
								<div class="img_cont_msg">
									<img src="<c:url value="/resources/image/${chat.avatar }"/>" class="rounded-circle user_img_msg">
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
			<div class="card-footer">
				<form:form action="${sendMessage}" method="POST">
					<div class="input-group">	
						<input type="hidden" name="chatId" value="${conversation.id }">
						<input type="hidden" name="userId" value="<%= session.getAttribute("id") %>">
						<input type="text" name="message" class="form-control type_msg" placeholder="Type your message...">
						<div class="input-group-append">
							<button type="submit" class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>