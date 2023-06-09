<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->

<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<%-- ${param.ticket_type} <!-- 티켓 -->
${param.start_point} <!-- 출발지 -->
${param.end_point} <!-- 도착지 -->
${param.start_date} <!-- 출국일 -->
${param.end_date} <!-- 귀국일 -->
${param.adults} <!-- 성인 수 -->
${param.teenagers} <!-- 청소년 수 -->
${paramValues.flight_code[0]} <!-- 왕복 (출국) -->
${paramValues.flight_code[1]} <!-- 왕복 (귀국) -->
${param.flight_code} <!-- 편도 -->
 --%>


<!---------- START CONTAINER ---------->
<form
	action="${pageContext.request.contextPath}/air_usupdate.air?reservation_number=${param.reservation_number}"
	method="post" id="form">
	<div id='airMain' class="body-top container-field">
		<div class="row">
			<div class="col-sm-12">
				<div class="airP1">
					<h3 class="heading-panel">내 예약 현황</h3>
					<h4 class="airS4 heading-panel">항공스케줄</h4>
					<hr />
					<div class="airBox">
						<div class="airList2">
							<ul>
								<li class="air2"><p>${list[0].aviDto.airline}</p>
									<p>${list[0].aviDto.flight}</p></li>
								<li class="st2-1">출발</li>
								<li class="time2"><p>${list[0].aviDto.start_point}</p>
									<p>${list[0].aviDto.departure_time}</p></li>
								<li class="st2-1">도착</li>
								<li class="time2"><p>${list[0].aviDto.end_point}</p>
									<p>${list[0].aviDto.arrival_time}</p></li>
							</ul>
						</div>
					</div>
					<h4 class="airS5 heading-panel">예약자 정보</h4>
					<hr />
					<div class="airP5">
						<table class="table table-striped table-bordered">
							<tbody>
								<tr>
									<th scope="row">예약자명</th>
									<td><input type="text" class="form-control"
										id="reservation_name" name="reservation_name"
										value="${list[0].reservation_name}" /></td>
								</tr>
								<tr>
									<th scope="row">휴대폰 번호</th>
									<td><input type="tel" class="form-control"
										id="telephone_number" name="telephone_number"
										value="${list[0].telephone_number}" /></td>
								</tr>
								<tr>
									<th scope="row">이메일</th>
									<td><input type="email" class="form-control" id="email"
										name="email" value="${list[0].email}" /></td>
								</tr>
							</tbody>
						</table>
					</div>

					<h4 class="airS5 heading-panel">여행자 정보</h4>
					<hr />
					<div class="airP5">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th scope="col">탑승객</th>
									<th scope="col">구분</th>
									<th scope="col">한글 성명</th>
									<th scope="col">영문 성명</th>
									<th class="airthg" scope="col">성별</th>
									<th scope="col">생년월일</th>
								</tr>
							</thead>
							<tbody class="airTb">
								<c:forEach var="dto" begin="0" items="${list}"
									end="${list[0].adults + list[0].teenagers -1}"
									varStatus="status">
									<tr>
										<td>탑승객${status.count}</td>
										<c:choose>
											<c:when test="${list[0].adults >= status.count}">
												<td>성인</td>
											</c:when>
											<c:otherwise>
												<td>청소년</td>
											</c:otherwise>
										</c:choose>
										<td><input type="text" class="form-control"
											id="passenger_korean_name" name="passenger_korean_name"
											value="${dto.passDto.passenger_korean_name}" readonly /></td>
										<td><input type="text" class="form-control"
											id="passenger_english_name" name="passenger_english_name"
											value="${dto.passDto.passenger_english_name}" readonly /></td>
										<td class="airtdg"><input type="text"
											class="airgg form-control" id="gender" name="gender"
											value="${dto.passDto.gender}" readonly /></td>
										<td><input type="text" class="form-control"
											id="birth_date" name="birth_date"
											value="${dto.passDto.birth_date}" readonly /></td>
								</c:forEach>
							</tbody>
						</table>
						<br /> <br /> <br />
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-block" title="수정"
								value="수정" />
						</div>
						<div class='form-group'>
							<input type="reset" class="btn btn-warning btn-block" title="취소"
								value="취소" />
						</div>
					</div>
					<div class="form-group">
						<a href="javascript:history.go(-1)"
							class="btn btn-default btn-block" title="이전화면">이전화면</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<!---------- END CONTAINER ---------->

<!---------- START SCRIPT ---------->
<script>
	$(document).ready(function() {
		$("#form").submit(function() {
			if ($("#reservation_name").val() == "") {
				alert("예약자명을 입력해주세요.");
				$("#reservation_name").focus();
				return false;
			}
			if ($("#telephone_number").val() == "") {
				alert("휴대폰 번호를 입력해주세요.");
				$("#telephone_number").focus();
				return false;
			}
			if ($("#email").val() == "") {
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return false;
			}
		});
	});
</script>
<!---------- END SCRIPT ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>