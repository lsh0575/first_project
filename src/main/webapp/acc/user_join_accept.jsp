<%@page import="java.sql.Connection"%>
<%@page import="com.threadpool.dbmanager.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "../inc/header.jsp" %>
	<div class="container body-top">
		<h3 class="text-center">Threadpool 이용약관</h3>
		<input type="checkbox" id="all_check">
		<label for="all_check">모두 동의하기</label>
		<div class="form-group">
			<input type="checkbox" id="check1" name="agree">
			<label for="check1">개인정보 이용 약관에 동의(필수)</label>
		</div>
		<div class="form-group text-center">
			<textarea cols="100" rows="10" readonly><%@ include file="../agree/privacy.txt"%></textarea>
		</div>
		<div class="form-group">
			<input type="checkbox" id="check2" name="agree">
			<label for="check2">개인정보 제3자 제공동의(필수)</label>
		</div>
		<div class="form-group text-center">
			<textarea cols="100" rows="10" readonly><%@ include file="../agree/personal_thirdparty.txt" %></textarea>
		</div>
		<div class="text-center">
			<p><a href="${pageContext.request.contextPath}/user_join.acc" class="btn btn-info" id="accept" title="약관에 동의합니다">동의</a>
		</div>
		<script>
			$("#document").ready(function(){
				$("#accept").on("click",function(){
					if ($("#check1").is(":checked")==false){
						alert('개인정보 이용 약관에 동의해주세요!');
						$("#check1").focus();
						return false;
					}
					else if ($("#check2").is(":checked")==false){
						alert('개인정보 제3자 제공에 동의해주세요!');
						$("#check2").focus();
						return false;
					}
				});						
			});
			$("#document").ready(function(){
				$("#all_check").on("click",function(){
					if ($("#all_check").is(":checked")==true){
						$(":checkbox[name=agree]").prop("checked",true);
					} else {
						$(":checkbox[name=agree]").prop("checked",false);
					}
				});
			});
				
		</script>
	</div>
<%@include file = "../inc/footer.jsp" %>