<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->
<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<!---------- START CONTAINER ---------->
<c:set var="dto" value="${list[0].reservation_number}" />
<div id="airMain" class="body-top container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">예약자 및 승객자 목록</h3>
				<div class="airTop">
					<span class="glyphicon glyphicon-plus">예약자명</span>
					<p>${list[0].reservation_name}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">ID</span>
					<p>${list[0].user_ID}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">전화번호</span>
					<p>${list[0].telephone_number}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">이메일</span>
					<p>${list[0].email}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">티켓 종류</span>
					<p>${list[0].ticket_type}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">성인 예약자 수</span>
					<p>${list[0].adults}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">청소년 예약자 수</span>
					<p>${list[0].teenagers}</p>
				</div>
				<div class="airCenter p3">
					<span class="glyphicon glyphicon-plus">총 결제 금액</span>
					<p>${list[0].total_payment}</p>
				</div>
				<div class="airBottom p3">
					<span class="glyphicon glyphicon-plus">예약 일자</span>
					<p>${list[0].reservation_date}</p>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="airSub" class="container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<table class="t3 table table-striped">
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col">탑승객 한국 이름</th>
							<th scope="col">탑승객 영어 이름</th>
							<th scope="col">성별</th>
							<th scope="col">생년월일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" begin="0" end="${list.size()-1}" step="1"
							items="${list}" varStatus="status">
							<c:choose>
								<c:when test="${status.index == status.end}">
									<tr>
										<td>${status.end+1}</td>
										<td>${dto.passDto.passenger_korean_name}</td>
										<td>${dto.passDto.passenger_english_name}</td>
										<td>${dto.passDto.gender}</td>
										<td>${dto.passDto.birth_date}</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td>${status.count}</td>
										<td>${dto.passDto.passenger_korean_name}</td>
										<td>${dto.passDto.passenger_english_name}</td>
										<td>${dto.passDto.gender}</td>
										<td>${dto.passDto.birth_date}</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="airP1">
		<div class='form-group'>
			<a
				href="${pageContext.request.contextPath}/air_reedit_view.air?reservation_number=${list[0].reservation_number}"
				class="btn btn-primary btn-block" title="수정 폼으로 가기">수정</a>
		</div>
		<div class='form-group'>
			<a
				href="${pageContext.request.contextPath}/air_redelete_view.air?reservation_number=${list[0].reservation_number}"
				class="btn btn-danger btn-block" title="삭제 폼으로 가기">삭제</a>
		</div>
		<div class="form-group">
			<a href="${pageContext.request.contextPath}/air_relist.air"
				class="btn btn-default btn-block" title="목록보기">목록보기</a>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>