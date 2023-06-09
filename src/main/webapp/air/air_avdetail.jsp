<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->
<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<!---------- START CONTAINER ---------->
<div id="airMain" class="container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">항공편 관리 목록</h3>
				<div class="airTop">
					<span class="glyphicon glyphicon-plus">항공편명</span>
					<p>${dto.flight}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">항공사</span>
					<p>${dto.airline}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">분류</span>
					<p>${dto.classification_of_flights}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">출발시간</span>
					<p>${dto.departure_time}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">도착시간</span>
					<p>${dto.arrival_time}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">좌석 수</span>
					<p>${dto.number_of_seats}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">좌석 등급</span>
					<p>${dto.seat_class}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">운임료</span>
					<p>${dto.freight_charge}</p>
				</div>
				<div class="airCenter">
					<span class="glyphicon glyphicon-plus">출발지</span>
					<p>${dto.start_point}</p>
				</div>
				<div class="airBottom">
					<span class="glyphicon glyphicon-plus">도착지</span>
					<p>${dto.end_point}</p>
				</div>
				<div class='form-group'>
					<a
						href="${pageContext.request.contextPath}/air_avedit_view.air?flight_code=${dto.flight_code}"
						class="btn btn-primary btn-block" title="수정 폼으로 가기">수정</a>
				</div>
				<div class='form-group'>
					<a
						href="${pageContext.request.contextPath}/air_avdelete_view.air?flight_code=${dto.flight_code}"
						class="btn btn-danger btn-block" title="삭제 폼으로 가기">삭제</a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/air_avlist.air"
						class="btn btn-default btn-block" title="목록보기">목록보기</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>