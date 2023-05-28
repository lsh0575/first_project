<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Threadpool project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/css.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">
<h1 class="thrdp_hidden">ThreadPool</h1>
<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}">
					<img src="${pageContext.request.contextPath}/img/main_logo.png" width="147" height="24" alt="Threadpool">
				</a>
			</div>
			<div class="" id="myNavbar">
				<h2 class="thrdp_hidden">헤더 메뉴</h2>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${sessionScope.account!=null}">
						<c:choose>
							<c:when test="${sessionScope.account.role_id eq 0}">
								<li class="dropdown">
									<a class="dropdown-toggle btn btn-thrdpheader" data-toggle="dropdown" href="">${sessionScope.account.id}
									<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/user.acc?id=${sessionScope.account.id}">마이페이지</a></li>
										<li><a href="#">항공예약조회</a></li>
										<li><a href="#">호텔예약조회</a></li>
										<li><a href="#">마음에 든 여행지</a></li>
										<li><a href="${pageContext.request.contextPath}/logout.acc">로그아웃</a></li>
									</ul>
								</li>
							</c:when>
							<c:when test="${sessionScope.account.role_id eq 1}">
								<li class="dropdown">
									<a class="dropdown-toggle btn btn-thrdpheader" data-toggle="dropdown" href="">${sessionScope.account.id}
									<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/user.acc?id=${sessionScope.account.id}">마이페이지</a></li>
										<li><a href="${pageContext.request.contextPath}/user_list.acc">고객정보 조회</a></li>
										<li><a href="${pageContext.request.contextPath}/logout.acc">로그아웃</a></li>
									</ul>
								</li>
							</c:when>
							<c:when test="${sessionScope.account.role_id eq 2}">
								<li class="dropdown">
									<a class="dropdown-toggle btn btn-thrdpheader" data-toggle="dropdown" href="">${sessionScope.account.id}
									<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/user.acc?id=${sessionScope.account.id}">마이페이지</a></li>
										<li><a href="#">상품관리</a></li>
										<li><a href="#">예약 및 리뷰 관리</a></li>
										<li><a href="${pageContext.request.contextPath}/logout.acc">로그아웃</a></li>
									</ul>
								</li>
							</c:when>
							<c:when test="${sessionScope.account.role_id eq 3}">
								<li class="dropdown">
									<a class="dropdown-toggle btn btn-thrdpheader" data-toggle="dropdown" href="">${sessionScope.account.id}
									<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/user.acc?id=${sessionScope.account.id}">마이페이지</a></li>
										<li><a href="#">상품관리</a></li>
										<li><a href="#">예약 및 리뷰 관리</a></li>
										<li><a href="${pageContext.request.contextPath}/logout.acc">로그아웃</a></li>
									</ul>
								</li>
							</c:when>
							<c:when test="${sessionScope.account.role_id eq 4}">
								<li class="dropdown">
									<a class="dropdown-toggle btn btn-thrdpheader" data-toggle="dropdown" href="">${sessionScope.account.id}
									<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/user.acc?id=${sessionScope.account.id}">마이페이지</a></li>
										<li><a href="#">상품관리</a></li>
										<li><a href="#">예약 및 리뷰 관리</a></li>
										<li><a href="${pageContext.request.contextPath}/logout.acc">로그아웃</a></li>
									</ul>
								</li>
							</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<li class="dropdown">
							<form action="${pageContext.request.contextPath}/login.acc" method="post">
								<a class="dropdown-toggle btn btn-thrdpheader" data-toggle="dropdown" href="" id="login_form">로그인
								<span class="caret"></span></a>
								
								<ul class="dropdown-menu thrdp-menu">
									<li><input class="width-full header-input" type="text" id="id_input" name="id" placeholder="id"></li>
									<li><input class="width-full header-input" type="password" id="pass_input" name="pass" placeholder="password"></li>
									<li><input type="submit" class="btn-thrdpheader width-full" value="log in" title="입력한 정보로 로그인합니다."></li>
									<li>
										<a href="${pageContext.request.contextPath}/idsearch.acc">아이디/비밀번호찾기</a>
									</li>
								</ul>
							</form>
							<li><a class="btn btn-thrdpheader" href="${pageContext.request.contextPath}/join.acc">회원가입</a><li>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<!-- header -->