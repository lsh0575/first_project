<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->
<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<!---------- START CONTAINER ---------->
<div id="airMain" class="body-top container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">항공편 예약자 관리 목록</h3>
				<form action="${pageContext.request.contextPath}/air_relist.air"
					method="get" id="form">
					<table class="table table-striped table-bordered">
						<tbody>
							<tr>
								<th scope="row">예약자명</th>
								<td><input type="text" class="form-control"
									id="reservation_name" name="reservation_name"
									placeholder="내용을 입력해주세요." /></td>
							</tr>
							<tr>
								<th scope="row">예약번호</th>
								<td><input type="text" class="form-control"
									id="reservation_number" name="reservation_number"
									placeholder="내용을 입력해주세요." /></td>
							</tr>
							<tr>
								<th scope="row">구분</th>
								<td><select class="form-control" id="ticket_type"
									name="ticket_type">
										<option value="편도">편도</option>
										<option value="왕복">왕복</option>
								</select></td>
							</tr>
							<tr>
								<th scope="row">예약일 검색</th>
								<td><input type="date" class="form-control" id="start_date"
									name="start_date" /> <input type="date" class="form-control"
									id="end_date" name="end_date" />
									<p></p> <input type="submit" id="where" name="where"
									class="btn btn-primary" value="검색" /> <input type="reset"
									class="btn btn-warning" value="취소" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- START CONTAINER ---------->
<div id="airSub" class="container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col">예약자명</th>
							<th scope="col">아이디</th>
							<th scope="col">예약번호</th>
							<th scope="col">구분</th>
							<th scope="col">예약일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" items="${page_count.list}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td><a
									href="${pageContext.request.contextPath}/air_redetail.air?reservation_number=${dto.reservation_number}"
									title="${dto.reservation_name}">${dto.reservation_name}</a></td>
								<td>${dto.user_ID}</td>
								<td>${dto.reservation_number}</td>
								<td>${dto.ticket_type}</td>
								<td>${dto.reservation_date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${page_count.pagesStart != 1}">
							<li><a
								href="${pageContext.request.contextPath}/air_relist.air?pageStartNum=${(page_count.pagesStart - 2) * page_count.onePageListCount}<c:if test="${param.where ne null}">&amp;reservation_name=${param.reservation_name}&amp;reservation_number=${param.reservation_number}&amp;ticket_type=${param.ticket_type}&amp;start_date=${param.start_date}&amp;end_date=${param.end_date}&amp;where=${param.where}</c:if>"
								class="btn btn-default" title="이전의 페이지들을 보여줍니다.">이전</a></li>
						</c:if>
						<c:forEach var="dto" begin="${page_count.pagesStart}"
							end="${page_count.pagesEnd}" step="1" varStatus="stat">
							<li
								<c:if test="${stat.index eq page_count.currentPages}">class="active"</c:if>>
								<a
								href="${pageContext.request.contextPath}/air_relist.air?pageStartNum=${(stat.index - 1) * page_count.onePageListCount}<c:if test="${param.where ne null}">&amp;reservation_name=${param.reservation_name}&amp;reservation_number=${param.reservation_number}&amp;ticket_type=${param.ticket_type}&amp;start_date=${param.start_date}&amp;end_date=${param.end_date}&amp;where=${param.where}</c:if>"
								title="${stat.index} 페이지로 이동합니다.">${stat.index}</a>
							</li>
						</c:forEach>
						<c:if test="${page_count.pageCount != page_count.pagesEnd}">
							<li><a
								href="${pageContext.request.contextPath}/air_relist.air?pageStartNum=${page_count.pagesEnd * page_count.onePageListCount}<c:if test="${param.where ne null}">&amp;reservation_name=${param.reservation_name}&amp;reservation_number=${param.reservation_number}&amp;ticket_type=${param.ticket_type}&amp;start_date=${param.start_date}&amp;end_date=${param.end_date}&amp;where=${param.where}</c:if>"
								class="btn btn-default" title="다음의 페이지들을 보여줍니다.">다음</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- START JAVASCRIPT ---------->
<script>
const startDateInput = document.getElementById('start_date');
const endDateInput = document.getElementById('end_date');

startDateInput.addEventListener('change', () => {
	  const startDate = new Date(startDateInput.value);
	  const endDate = new Date(endDateInput.value);

	  if (endDate < startDate) {
	    endDateInput.value = '';
	    alert('시작날짜 다시 입력해주세요.');
	  }
	});

	endDateInput.addEventListener('change', () => {
	  const startDate = new Date(startDateInput.value);
	  const endDate = new Date(endDateInput.value);

	  if (endDate < startDate) {
	    endDateInput.value = '';
	    alert('끝날짜 다시 입력해주세요.');
	  }
	});
	
$(document).ready(function() {
	$("#form").submit(function() {
		if($("#reservation_number").val() == "") {
		alert("예약자명을 입력해주세요.");
		$("#reservation_number").focus();
		return false;
	}
		if($("#reservation_number").val() == "") {
			alert("예약번호를 입력해주세요.");
			$("#reservation_number").focus();
			return false;
		}
		if($("#start_date").val() == "") {
			alert("날짜를 선택해주세요.");
			$("#start_date").focus();
			return false;
		}
		if($("#end_date").val() == "") {
			alert("날짜를 선택해주세요.");
			$("#end_date").focus();
			return false;
		}
	});
});
</script>
<!---------- END JAVASCRIPT ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>