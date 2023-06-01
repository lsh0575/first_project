<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="body-top container">
	<%-- Message List --%>
	<h3 class="text-center">수신된 메세지 목록</h3>
	<form action="${pageContext.request.contextPath}/delete.msg" method="post">
		<table class="table table-striped">
			<colgroup>
				<col style="width:5%">
			<col style="width:15%">
			<col style="width:39%">
			<col style="width:17%">
			<col style="width:17%">
			<col style="width:7%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="check_all"/></th>
					<th scope="col">작성자</th>
					<th scope="col">제목</th>
					<th scope="col">수신 시간</th>
					<th scope="col">읽은 시간</th>
					<th scope="col">읽음</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="msg" items="${paging.list}">
				<tr>
					<td><input type="checkbox" class="check" name="check" value="${msg.msg_recieve_no}"/></td>
					<td>${msg.msg_sender}</td>
					<td><a href="${pageContext.request.contextPath}/read.msg?msgno=${msg.msg_recieve_no}">${msg.msg_title}</a></td>
					<td>${msg.msg_send_time}</td>
					<td>${msg.msg_read_time}</td>
					<td>${msg.msg_read?'읽음':'안읽음'}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- Message List --%>
		<%-- Paging --%>
		<div class="text-center">
				<ul class="pagination">
					<c:if test="${paging.pagesStart!=1}">
						<li>
							<a href="${pageContext.request.contextPath}/user_list.acc?pageStartNum=${(paging.pagesStart-2)*paging.onePageListCount}"
							class="btn btn-default" title="이전의 페이지들을 보여줍니다.">이전</a>
						</li>
					</c:if>
					<c:forEach var="dto" begin="${paging.pagesStart}" end="${paging.pagesEnd}" step="1" varStatus="stat">
						<li <c:if test="${stat.index eq paging.currentPages}">class="active"</c:if> >
							<a href="${pageContext.request.contextPath}/list.msg?pageStartNum=${(stat.index-1)*paging.onePageListCount}"
							 title="${stat.index} 페이지로 이동합니다.">${stat.index}</a>
						</li>
					</c:forEach>
					<c:if test="${paging.pageCount!=paging.pagesEnd}">
						<li>
							<a href="${pageContext.request.contextPath}/list.msg?pageStartNum=${(paging.pagesEnd)*paging.onePageListCount}"
							class="btn btn-default" title="다음의 페이지들을 보여줍니다.">다음</a>
						</li>
					</c:if>
				</ul>
			</div> 
			<%-- Paging --%>
		<div class="form-group text-right">
			<input type="submit" value="삭제" class="btn btn-danger" title="선택한 글을 삭제합니다."/>
			<a href="${pageContext.request.contextPath}/write.msg" title="글을 작성하러 갑니다." class="btn btn-default">작성</a>
		</div>
	</form>
	<script>
	$(document).ready(function(){
		////전체선택
		$("#check_all").on("click",function(){
			if ($("#check_all").is(":checked")==true){
				$(":checkbox[name=check]").prop("checked",true);
			} else {
				$(":checkbox[name=check]").prop("checked",false);
			}
		});
	});
	</script>
</div>
<%@ include file="/inc/footer.jsp" %>