<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
<div class="body-top container">
	<!-- 메세지 -->
	<h3 class="text-center">메세지 읽기</h3>
	<div>
		<strong>제목</strong>
		<p>${msg.msg_title}</p>
	</div>
	<div>
		<strong>발신자</strong>
		<p>${msg.msg_sender}</p>
		<p>${msg.msg_send_time} 작성됨.</p>
	</div>
	<hr/>
	<div class="form-group">
		<p><strong>내용</strong></p>
		<textarea rows="15" class="form-control" style="background-color:white;" readonly>${msg.msg_context}</textarea>
	</div>
	<div class="form-group">
		<div class="col-sm-3"></div>
		<div class="col-sm-3"><a href="${pageContext.request.contextPath}/list.msg" class="btn btn-success btn-block">목록으로</a></div>
		<div class="col-sm-3"><a href="${pageContext.request.contextPath}/delete.msg?msgno=${msg.msg_recieve_no}" class="btn btn-danger btn-block">삭제</a></div>
		<div class="col-sm-3"></div>
	</div>
	<!-- 메세지 -->	
</div>
<%@ include file="/inc/footer.jsp" %>
