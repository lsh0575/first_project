<%@page import="java.sql.Connection"%>
<%@page import="com.threadpool.dbmanager.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "../inc/header.jsp" %>
	<div class="container" style="margin-top:300px;">
				
		<div class="form-group">
			<a class="btn btn-info form-control" href="${pageContext.request.contextPath}/user_join_agree.acc">일반 사용자 회원가입</a>
		</div>
		<div class="form-group">
			<a class="btn btn-info form-control" href="${pageContext.request.contextPath}/business_join_agree.acc">사업자 회원가입</a>
		</div>
				
	</div>
<%@include file = "../inc/footer.jsp" %>