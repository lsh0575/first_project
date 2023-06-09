<%@page import="java.util.ArrayList"%>
<%@page import="com.threadpool.dto.air.ReservationDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->

<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<div id='airMain' class="body-top container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">내 예약</h3>
				<hr />
				<c:forEach var="dto" items="${list}" varStatus="status">
					<c:choose>
						<c:when test="${dto.ticket_type == '왕복'}">
							<div class="airBox">
								<div class="LairBox">
									<h4 class="air4">왕복</h4>
								</div>
								<div class="airList3-1">
									<ul>
										<li class="air3"><p>${dto.aviDto.airline}</p>
											<p>${dto.aviDto.flight}</p></li>
										<li class="st3-1">출발</li>
										<li class="time3"><p>${dto.aviDto.start_point}</p>
											<p>${dto.aviDto.departure_time}</p></li>
										<li class="st3-1">도착</li>
										<li class="time3"><p>${dto.aviDto.end_point}</p>
											<p>${dto.aviDto.arrival_time}</p></li>
										<li class="no3"><p>예약번호</p>
											<p>${dto.reservation_number}</p></li>
										<li><a href="${pageContext.request.contextPath}/air_usdetail.air?reservation_number=${dto.reservation_number}" class="bt3 btn-primary" title="상세보기">변경/삭제하기</a>
										</li>
									</ul>
								</div>
							</div>
						</c:when>
						<c:when test="${dto.ticket_type == '편도'}">
							<div class="airBox">
								<div class="LairBox">
									<h4 class="air4">편도</h4>
								</div>
								<div class="airList3-2">
									<ul>
										<li class="air3"><p>${dto.aviDto.airline}</p>
											<p>${dto.aviDto.flight}</p></li>
										<li class="st3-1">출발</li>
										<li class="time3"><p>${dto.aviDto.start_point}</p>
											<p>${dto.aviDto.departure_time}</p></li>
										<li class="st3-1">도착</li>
										<li class="time3"><p>${dto.aviDto.end_point}</p>
											<p>${dto.aviDto.arrival_time}</p></li>
										<li class="no3"><p>예약번호</p>
											<p>${dto.reservation_number}</p></li>
										<li><a href="${pageContext.request.contextPath}/air_usdetail.air?reservation_number=${dto.reservation_number}" class="bt3 btn-primary" title="상세보기">변경/삭제하기</a>
										</li>
									</ul>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>