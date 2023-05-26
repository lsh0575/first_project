<%@page import="java.sql.Connection"%>
<%@page import="com.threadpool.dbmanager.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "../inc/header.jsp" %>
	<!-- 사용자 회원가입 폼 -->
	<div class="container body-top">
		
		<form action="${pageContext.request.contextPath}/user_edit_act.acc?id=${param.id}" method="post" id="form">
			<fieldset>
				<legend class="text-center">${param.account.id} 정보수정</legend>
				<p>*표시는 필수 입력사항입니다.</p>
				
				<div class="form-group">
					<label for="name_form_input">이름 입력(*)</label>
					<input type="text" class="form-control" id="name_form_input" name="name" placeholder="이름을 입력해주세요" value="${account_detail.name}"/>
				</div>
				<div class="form-group">
					<label for="birth_form_input">생년월일 입력(*)</label>
					<input type="date" class="form-control" id="birth_form_input" name="birth" placeholder="생년월일을 입력해주세요" value="${account_detail.birth}"/>
				</div>
				<div class="form-group">
					<label for="email_form_input">이메일 입력(*)</label>
					<input type="email" class="form-control" id="email_form_input" name="email" placeholder="이메일을 입력해주세요" value="${account_detail.email}"/>
				</div>
				<div class="form-group">
					<label for="phone_form_input">휴대전화번호 입력(*)</label>
					<input type="text" class="form-control" id="phone_form_input" name="phonenum" placeholder="휴대전화번호를 입력해주세요" value="${account_detail.phonenum}"/>
				</div>
				<div class="form-group">
					<div style="width:200px;">
						<label for="postnum_form_input">우편번호 입력(5자리)(*)</label>
						<input type="text" class="form-control" id="postnum_form_input" name="postnum" placeholder="우편번호를 입력해주세요" value="${account_detail.postnum}" maxlength="5"/>
					</div>
				</div>
				<div class="form-group">
					<label for="postnum_form_input">도로명 주소 입력</label>
					<input type="text" class="form-control" id="basic_addr_form_input" name="basic_addr" placeholder="우편번호를 입력하면 채워집니다." value="${account_detail.address}" readonly/>
				</div>
				<div class="form-group">
					<label for="addr_form_input">상세주소 입력(*)</label>
					<input type="text" class="form-control" id="addr_form_input" name="addr" placeholder="상세주소를 입력해주세요" value="${account_detail.detail_address}"/>
				</div>
				<div class="form-group">
					<label for="pass_form_input">비밀번호 입력(*)</label>
					<input type="password" class="form-control" id="pass_form_input" name="pass" 
					<c:if test="${sessionScope.account.role_id eq 1}">value="${account_detail.pass}" display:none</c:if>
					placeholder="비밀번호를 입력해주세요"/>
				</div>
				<div class="form-group">
					<div class="col-sm-4"><input type="submit" class="btn btn-success form-control" value="정보 수정"/></div>
					<div class="col-sm-4"><input type="reset" class="btn btn-success form-control" value="다시 수정하기"/></div>
					<div class="col-sm-4"><a class="btn btn-danger form-control" href="${pageContext.request.contextPath}/user.acc?id=${param.id}" title="${param.name}의 정보로 돌아갑니다.">돌아가기</a></div>
				</div>
			</fieldset>
		</form>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		$("#document").ready(function(){
			$("#postnum_form_input").on("click",function(){
				new daum.Postcode({
			        oncomplete: function(data) {
			        	$("#postnum_form_input").val(data.zonecode);
			            $("#basic_addr_form_input").val(data.roadAddress);
			            $("#addr_form_input").focus();
			        }
			    }).open();
			});
			$("#basic_addr_form_input").on("click",function(){
				$("#postnum_form_input").click();
			});
			$("#form").on("submit",function(){
				} else if ($("#pass_form_input").val().trim()==""){
					alert('비밀번호를 입력해주세요.');
					$("#pass_form_input").focus();
					return false;
				} else if ($("#name_form_input").val().trim()==""){
					alert('이름을 입력해주세요.');
					$("#name_form_input").focus();
					return false;
				} else if ($("#birth_form_input").val()==""){
					alert('생일을 입력해주세요.');
					$("#birth_form_input").focus();
					return false;
				}else if ($("#email_form_input").val()==""){
					alert('이메일을 입력해주세요.');
					$("#email_form_input").focus();
					return false;
				}else if ($("#phone_form_input").val()==""){
					alert('휴대전화번호를 입력해주세요.');
					$("#phone_form_input").focus();
					return false;
				}else if ($("#postnum_form_input").val()==""){
					alert('우편번호를 입력해주세요.');
					$("#postnum_form_input").focus();
					return false;
				}else if ($("#addr_form_input").val()==""){
					alert('상세주소를 입력해주세요.');
					$("#addr_form_input").focus();
					return false;
				}
			});						
		});
		</script>
	</div>
	<!-- 사용자 회원가입 폼 -->
<%@include file = "../inc/footer.jsp" %>