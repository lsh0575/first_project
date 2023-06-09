<%@page import="java.sql.Connection"%>
<%@page import="com.threadpool.dbmanager.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "../inc/header.jsp" %>
	<!-- 비밀번호 수정 폼 -->
	<div class="container body-top">
		
		<form action="${pageContext.request.contextPath}/user_pass_edit_act.acc?id=${param.id}" method="post" id="form">
			<fieldset>
				<legend class="text-center">${param.id} 비밀번호 수정</legend>
				<p>*표시는 필수 입력사항입니다.</p>
				<div class="form-group">
					<label for="pass_form_input">비밀번호 입력(*)</label>
					<input type="password" class="form-control" id="pass_form_input" name="pass"
					placeholder="비밀번호를 입력해주세요"/>
				</div>
				<div class="form-group">
					<label for="new_pass_form_input">새 비밀번호 입력(*)</label>
					<input type="password" class="pass_form form-control" id="new_pass_form_input" name="newpass"
					placeholder="비밀번호를 입력해주세요"/>
				</div>
				<div class="form-group">
					<label for="new_pass_re">새 비밀번호 확인(*)</label><span id="pass_confirm"></span>
					<input type="password" class="pass_form form-control" id="new_pass_re"
					placeholder="비밀번호를 입력해주세요"/>
				</div>
				<div class="form-group">
					<div class="col-sm-4"><input type="submit" class="btn btn-success form-control" value="정보 수정"/></div>
					<div class="col-sm-4"><input type="reset" class="btn btn-success form-control" value="다시 수정하기"/></div>
					<div class="col-sm-4"><a class="btn btn-danger form-control" href="${pageContext.request.contextPath}/user.acc?id=${param.id}" title="${param.name}의 정보로 돌아갑니다.">돌아가기</a></div>
				</div>
			</fieldset>
		</form>
		<script>
		$("#document").ready(function(){
			
		////비밀번호 입력 확인
			$(".pass_form").on("keyup",function(){
				if ($("#new_pass_re").val().trim()!=""){
					$.ajax({
						url:"${pageContext.request.contextPath}/pass_confirm.acc",
						type:"get",
						dataType:"html",
						data:{"pass":$('#new_pass_form_input').val(),
								"pass_re":$('#new_pass_re').val()},
						success:function(data){
							$("#pass_confirm").html(data);
						},
						error:function(xhr,textStatus,errorThrown){
							$("#id_is_dupl").html(xhr.status + "-"+textStatus+":"+errorThrown);
						}
					})
				}
			});
		
		////빈칸체크
			$("#form").on("submit",function(){
				if ($("#pass_form_input").val().trim()==""){ //비밀번호 빈칸 체크
					alert('비밀번호를 입력해주세요.');
					$("#pass_form_input").focus();
					return false;
				}else if ($("#new_pass_form_input").val().trim()==""){ //비밀번호 빈칸 체크
					alert('새 비밀번호를 입력해주세요.');
					$("#new_pass_form_input").focus();
					return false;
				}else if ($("#pass_check").data("check") != 'checked'){ //비밀번호 확인
					if ($("#new_pass_form_input").val()==$("#new_pass_re").val()){ //바로 엔터쳐서 ajax 못들어가는경우 여기서 체크
						return true;
					}
					alert ('비밀번호가 일치하지 않습니다.');
					$("#new_pass_form_input").focus();
					return false;
				}
			});
		});	
		</script>
	</div>
	<!-- 비밀번호 수정 폼 -->
<%@include file = "../inc/footer.jsp" %>