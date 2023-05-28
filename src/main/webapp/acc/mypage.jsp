<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file= "../inc/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- MyPage -->
	<div class="container body-top text-center">
		<h4>${requestScope.account_detail.id} : ${requestScope.account_detail.name}</h4>
		<c:if test="${requestScope.account_detail.out_date!=null}">
		<p>
			현재 탈퇴한 계정입니다.
		</p>
		</c:if>
		<p>
			<img class="btn" src="${requestScope.account.pic}" alt="프로필 이미지" width="200px" height="200px">
		</p>
		<!-- Detail Accountinfo -->
		<div class="col-sm-12">
			<table class="table table-striped">
				<tbody class="text-left">
					<tr>
						<th scope="row">아이디</th>
						<td>${requestScope.account_detail.id}</td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td>${requestScope.account_detail.name}</td>
					</tr>
					<tr>
						<th scope="row">생년월일</th>
						<td>${requestScope.account_detail.birth}</td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>${requestScope.account_detail.email}</td>
					</tr>
					<tr>
						<th scope="row">휴대전화번호</th>
						<td>${requestScope.account_detail.phonenum}</td>
					</tr>
					<tr>
						<th scope="row">우편번호</th>
						<td>${requestScope.account_detail.postnum}</td>
					</tr>
					<tr>
						<th scope="row">상세주소</th>
						<td>${requestScope.account_detail.address} ${requestScope.account_detail.detail_address}</td>
					</tr>
					<tr>
						<th scope="row">가입날짜</th>
						<td>${requestScope.account_detail.create_date}</td>
					</tr>
					<tr>
						<th scope="row">계정 유형</th>
						<td>${requestScope.account_detail.role_name}</td>
					</tr>
					<c:if test="${requestScope.account_detail.role_id>1}">
						<tr>
							<th scope="row">사업자등록번호</th>
							<td>${requestScope.account_detail.company_num}</td>
						</tr>
					</c:if>
					<c:if test="${requestScope.account_detail.out_date!=null}">
						<tr>
							<th scope="row">탈퇴 날짜</th>
							<td>${requestScope.account_detail.out_date}</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<!-- Detail Accountinfo -->
		<!-- Buttons -->
		<c:if test="${param.id eq sessionScope.account.id || sessionScope.account.role_id eq 1}">
			<div class="conatiner">
				<div class="col-sm-4"> <a class="btn btn-info btn-block" href="${pageContext.request.contextPath}/user_edit.acc?id=${param.id}">정보수정</a> </div>
				<div class="col-sm-4"> <a class="btn btn-warning btn-block" href="${pageContext.request.contextPath}/user_pass_edit.acc?id=${param.id}">비밀번호 수정</a> </div>
				<div class="col-sm-4"> <a class="btn btn-danger btn-block" href="${pageContext.request.contextPath}/user_out.acc?id=${param.id}">회원탈퇴</a> </div>
			</div>
		</c:if>
		<!-- Buttons -->
	</div>
	<!-- MyPage -->
<%@include file = "../inc/footer.jsp" %>