<%@page import="java.sql.Connection"%>
<%@page import="com.threadpool.dbmanager.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "../inc/header.jsp" %>
	<!-- 회원탈퇴 폼 -->
	<div class="container body-top">
		
		<form action="${pageContext.request.contextPath}/user_out_act.acc?id=${param.id}" method="post" id="form">
			<fieldset>
				<legend class="text-center">${param.id} 회원 탈퇴</legend>
				<strong>정말로 탈퇴하시겠습니까? 비밀번호를 입력해주세요.</strong>
				<div class="form-group">
					<label for="pass_form_input">비밀번호 입력(*)</label>
					<input type="password" class="form-control" id="pass_form_input" name="pass"
					placeholder="비밀번호를 입력해주세요"/>					
				</div>
				<div class="form-group">
					<label for="out_reason"></label>
					<textarea name="out_reason" class="form-control" rows="10" id="out_reason" placeholder="탈퇴 사유를 남겨주시면 앞으로의 서비스 향상에 도움이 됩니다."></textarea>
				</div>
				<div class="form-group text-center">
					<div class="col-sm-6">
						<input type="submit" class="btn btn-danger btn-block" value="회원탈퇴"/>
					</div>
					<div class="col-sm-6">
						<p><a href="${pageContext.request.contextPath}/user.acc?id=${param.id}" class="btn btn-success btn-block">돌아가기</a></p>
					</div>
				</div>
			</fieldset>
		</form>
		<script>
		$("#document").ready(function(){
			
		////빈칸체크
			$("#form").on("submit",function(){
				if ($("#pass_form_input").val().trim()==""){ //비밀번호 빈칸 체크
					alert('비밀번호를 입력해주세요.');
					$("#pass_form_input").focus();
					return false;
				}
				return true;
			});
		});	
		</script>
	</div>
	<!-- 회원탈퇴 폼 -->
<%@include file = "../inc/footer.jsp" %>