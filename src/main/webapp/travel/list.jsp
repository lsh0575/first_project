<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../inc/header.jsp"%>

<style>
.imgbox img {
	width: 800px;
	height: 400px;
	overflow: hidden;
	object-fit: cover;
	display: flex;
}

.imgbox {
	width: 90%
}

.search-container {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	margin: 10px;
}

.search-select, .searchValue, .search-button, .writeForm {
	height: 35px;
	padding: 4px;
	margin: 10px;
	font-size: 14px;
}
</style>

<div class="container text-center list body-top">
	<form action="${pageContext.request.contextPath}/detail.travel"
		method="get" id="travelSearch">
		<fieldset>
			<h3>Travel-List</h3>
			<div class="search-container">
				<select id="search-select" name="search-select"
					class="search-select">
					<option value="tno">글번호</option>
					<option value="tname">작성자</option>
				</select> <input type="text" name="searchValue" class="searchValue"
					placeholder="검색어를 입력하세요"> <input type="submit" value="조회"
					class="search-button">
			</div>
			<p class="writeForm text-right">
				<a href="${pageContext.request.contextPath}/write_view.travel"
					title="글쓰기 폼으로 이동합니다." class="btn btn-info">글쓰기</a> 
		        <a href="${pageContext.request.contextPath}" title="메인 화면으로 이동합니다." class="btn btn-lg">
		          <span class="glyphicon glyphicon-home"></span>
		        </a>
		</fieldset>
	</form>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(function() {
			$("#travelSearch").submit(function() {
				var searchValue = $(".searchValue").val();
				if (searchValue.trim() === "") {
					alert("검색어를 입력하세요.");
					$(".searchValue").focus();
					return false;
				}
			});
		});
	</script>
	<form action="" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>여행 리스트</legend>
			<div class="container">
				<table class="table table-striped">
					<c:forEach var="dto" items="${travel.list10}" varStatus="status">
						<thead>
							<tr>
								<th><span class="glyphicon glyphicon-ok"></span> NO :
									${dto.tno} <c:if test="${not empty dto.ttitle}">
										<span class="glyphicon glyphicon-ok"></span>[ TITLE : ${dto.ttitle} ]
								</c:if> <c:if test="${not empty dto.tupload_date}">
										<span class="glyphicon glyphicon-ok"></span>DATE : ${dto.tupload_date} </c:if>
									<c:if test="${not empty dto.thit}">
										<span class="glyphicon glyphicon-ok"></span>HIT : ${dto.thit}
								</c:if></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<p class="imgbox img-rounded">
										<a
											href="${pageContext.request.contextPath}/detail.travel?tno=${dto.tno}">
											<img src="${pageContext.request.contextPath}/upload/${dto.timages_1}" alt="${dto.ttitle}">
										</a>
									</p>
								</td>
							</tr>
					</c:forEach>
					</tbody>

					<!-- 페이징 시작  -->
					<tfoot>
						<tr>
							<td colspan="5" class="text-center">
								<ul class="pagination">
									<c:if test="${travel.bottomStart >= travel.bottomPageLimit}">
										<li><a
											href="${pageContext.request.contextPath}/paging.travel?pstartno=${travel.bottomStart-travel.onePageLimit}">이전</a></li>
									</c:if>
									<!-- 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 -->
									<c:forEach var="i" begin="${travel.bottomStart}"
										end="${travel.bottomEnd}">
										<li
											<c:if test="${travel.bottomCurrent == i}">class="active"</c:if>><a
											href="${pageContext.request.contextPath}/paging.travel?pstartno=${(i-1)*travel.onePageLimit}"
											title="">${i}</a></li>
									</c:forEach>
									<!-- 다음 -->
									<c:if test="${travel.bottomEnd < travel.bottomPageAll}">
										<li><a
											href="${pageContext.request.contextPath}/paging.travel?pstartno=${travel.bottomEnd*travel.bottomPageLimit}">다음</a></li>
									</c:if>
								</ul>
							</td>
						</tr>
					</tfoot>
					<!-- 페이징  끝  -->
				</table>
			</div>
		</fieldset>
	</form>
</div>


<%@ include file="../inc/footer.jsp"%>
