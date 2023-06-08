<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container panel panel-info">
			<table class="table table-striped">
				<caption>나의 예약 현황</caption>	
					<thead>
						<tr>
							<th scope="col">상품번호</th>
							<th scope="col">상품명</th>
							<th scope="col">상품타입</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var ="dto" items="${paging.list10}" varStatus="status">
						<tr> <%--  256 , 246 , 236     (전체페이지수(paging.pstartno)-status.index) --%>
							<td>${paging.pageTotal - paging.pstartno - status.index}</td>
							<td><a href="${pageContext.request.contextPath}/user_deleteView.hotel?hno=${dto.hno}">${dto.hname}</a></td>
							<td>${dto.reserveTime}</td>
						</tr>
						</c:forEach>
					</tbody>
			</table>
			<div class="form-group text-center" style="margin-top: 20px">
					<select id="select" name="select"  >
						<option>등록순</option>
						<option>이름순</option>
						<option>국가별</option>
					</select>
					
					
					<input type="search" id="search" ><input type="button" id="btn" name="btn" value="검색">
					
			</div>
			<div class="text-center">
			 <ul class="pagination">
				<!-- 	이전(90) 	 11(100)	-->
				<c:if test="${paging.bottomStart >= paging.bottomPageLimit}">
				<li><a 
				href="${pageContext.request.contextPath}/user_reservelist.hotel?pstartno=${(paging.bottomStart-2)*paging.onePageLimit}">이전</a></li>
				</c:if>
				<!-- 	1 2 3 4 5 6 7 8 9 10(90)  다음>>   11(100) -->
				<!-- 	11 12 13 14 15 16 17 18 19 20(190) 다음>>21(200) -->
				<c:forEach var="i" begin="${paging.bottomStart}" end="${paging.bottomEnd}">
				<li  <c:if test="${paging.bottomCurrent == i}"> class="active"</c:if>  ><a
				 href="${pageContext.request.contextPath}/user_reservelist.hotel?pstartno=${(i-1)*paging.onePageLimit}" 
				title="">
					${i}
				</a></li>
				</c:forEach>
				<!-- 	다음		전체페이지 26 / 10  -->
				<c:if test="${paging.bottomEnd < paging.bottomPageAll}">
				<li><a
				 href="${pageContext.request.contextPath}/user_reservelist.hotel?pstartno=${(paging.bottomEnd)*paging.bottomPageLimit}">다음</a></li>
				</c:if>
			</ul>
			</div>
			<p class="text-right"><a href="${pageContext.request.contextPath}/user_view.hotel" class = "btn btn-danger">예약하러 가기~</a></p>
	</div>
</body>
</html>