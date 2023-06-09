<%@page import="com.threadpool.dto.TravelDto"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.threadpool.dbmanager.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp" %>

	<div class="container body-top">
		<h3>Travel Delete</h3>
		<form action="${pageContext.request.contextPath}/delete.travel?tno=${param.tno}" method="post" id="delete_form">
			<div class="form-group">
				<label for="tpass">비밀번호</label>
				<input type="password" name="tpass" id="tpass" class="form-control">
				<span>(*)삭제 시 필수 입니다.</span>			
			</div>
			<div class="form-group">
				<input type="submit" value="확인" class="btn btn-danger">
				<input type="reset" onclick="history.go(-1);" value="취소" class="btn btn-danger">
			</div>
		</form>
	</div>
	<script>
	$(function(){
		  $("#delete_form").on("submit", function() {
		    if ($("#tpass").val() == "") {
		      $("#tpass").focus();
		      alert("패스워드를 입력해주세요.");
		      return false;
		    }
		  });

		});
	</script>


<%@include file="../inc/footer.jsp" %>