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
<c:choose>
	<c:when test="${param.ticket_type == '왕복'}">
		<form
			action="${pageContext.request.contextPath}/air_usinsert.air?flight_code=${paramValues.flight_code[0]}&flight_code=${paramValues.flight_code[1]}"
			method="post" id="form">
			<input type="hidden" name="total"
				value="${(list[0].freight_charge + list[1].freight_charge) * (param.adults + param.teenagers)}" />
	</c:when>
	<c:when test="${param.ticket_type == '편도'}">
		<form
			action="${pageContext.request.contextPath}/air_usinsert.air?flight_code=${paramValues.flight_code[0]}"
			method="post" id="form">
			<input type="hidden" name="total"
				value="${list[0].freight_charge * (param.adults + param.teenagers)}" />
	</c:when>
</c:choose>
<input type="hidden" name="adults" value="${param.adults}" />
<input type="hidden" name="teenagers" value="${param.teenagers}" />
<input type="hidden" name="ticket_type" value="${param.ticket_type}" />
<div id='airMain' class="body-top container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">항공 예약하기</h3>
				<h4 class="airS4 heading-panel">항공스케줄</h4>
				<hr />
				<div class="airBox">
					<div class="LairBox">
						<h4 class="air4">출국</h4>
					</div>
					<div class="airList2">
						<ul>
							<li class="air2"><p>${list[0].airline}</p>
								<p>${list[0].flight}</p></li>
							<li class="st2-1">출발</li>
							<li class="time2"><p>${list[0].start_point}</p>
								<p>${list[0].departure_time}</p></li>
							<li class="st2-1">도착</li>
							<li class="time2"><p>${list[0].end_point}</p>
								<p>${list[0].arrival_time}</p></li>
						</ul>
					</div>
				</div>
				<c:choose>
					<c:when test="${param.ticket_type == '왕복'}">
						<div class="airBox">
							<div class="LairBox">
								<h4 class="air4">입국</h4>
							</div>
							<div class="airList2">
								<ul>
									<li class="air2"><p>${list[1].airline}</p>
										<p>${list[1].flight}</p></li>
									<li class="st2-1">출발</li>
									<li class="time2"><p>${list[1].start_point}</p>
										<p>${list[1].departure_time}</p></li>
									<li class="st2-2">도착</li>
									<li class="time2"><p>${list[1].end_point}</p>
										<p>${list[1].arrival_time}</p></li>
								</ul>
							</div>
						</div>
					</c:when>
				</c:choose>


				<h4 class="airS5 heading-panel">인원 및 요금정보</h4>
				<hr />
				<div class="airP5">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">성인 수</th>
								<th scope="col">청소년 수</th>
								<th scope="col">인원 총합</th>
								<th scope="col">운임료</th>
								<th scope="col">운임료 총힙</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${param.adults}</td>
								<td>${param.teenagers}</td>
								<td>${param.adults + param.teenagers}</td>
								<c:choose>
									<c:when test="${param.ticket_type == '왕복'}">
										<td>${list[0].freight_charge + list[1].freight_charge}</td>
										<td>${(list[0].freight_charge + list[1].freight_charge) * (param.adults + param.teenagers)}</td>
									</c:when>
									<c:when test="${param.ticket_type == '편도'}">
										<td>${list[0].freight_charge}</td>
										<td>${list[0].freight_charge * (param.adults + param.teenagers)}</td>
									</c:when>
								</c:choose>
							</tr>
							<%-- 							<%
							 request.setAttribute("freight_charge", list.getFreightCharge());
							%>
 --%>
					</table>
					<h4 class="airS5 heading-panel">인원 및 요금정보</h4>
					<p class="airP5">예약자의 이메일과 휴대폰번호를 정확하게 입력해주세요.</p>
					<hr />
					<div class="airP5">
						<table class="table table-striped table-bordered">
							<tbody>
								<tr>
									<th scope="row">예약자명</th>
									<td><input type="text" class="form-control"
										id="reservation_name" name="reservation_name"
										placeholder="예약자명을 입력하세요." /></td>
								</tr>
								<tr>
									<th scope="row">휴대폰 번호</th>
									<td><input type="tel" class="form-control"
										id="telephone_number" name="telephone_number"
										pattern="^01[016789]-[0-9]{4}-[0-9]{4}$" required
										oninput="validateTelephoneNumber(this)"
										placeholder="휴대폰번호를 입력하세요." /></td>
								</tr>
								<tr>
									<th scope="row">이메일</th>
									<td><input type="email" class="form-control" id="email"
										name="email" placeholder="이메일을 입력하세요." /></td>
								</tr>
							</tbody>
						</table>
					</div>

					<h4 class="airS5 heading-panel">야행자정보</h4>
					<p class="airP5">탑승객의 정보를 정확하게 입력해주세요.</p>
					<hr />
					<div class="airP5">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th scope="col">탑승객</th>
									<th scope="col">구분</th>
									<th scope="col">한글 성명</th>
									<th scope="col">영문 성명</th>
									<th scope="col">성별</th>
									<th scope="col">생년월일</th>
								</tr>
							</thead>
							<tbody class="airTb">
								<c:forEach var="dto" begin="1"
									end="${param.adults + param.teenagers}" step="1"
									varStatus="status">
									<tr>
										<td>탑승객${status.count}</td>
										<c:choose>
											<c:when test="${param.adults >= status.count}">
												<td>성인</td>
											</c:when>
											<c:otherwise>
												<td>청소년</td>
											</c:otherwise>
										</c:choose>
										<td><input type="text" class="form-control"
											id="passenger_korean_name" name="passenger_korean_name"
											placeholder="한글성명을 입력하세요." /></td>
										<td><input type="text" class="form-control"
											id="passenger_english_name" name="passenger_english_name"
											placeholder="영문성명을 입력하세요." /></td>
										<td><input type="radio" class="airR0"
											id="gender${status.count}" name="gender${status.count}"
											value="남" /><label for="gender${status.count}">남</label> <input
											type="radio" class="airR0" id="gender${status.count}"
											name="gender${status.count}" value="여" /><label
											for="gender${status.count}">여</label></td>
										<td><input type="date" class="form-control"
											id="birth_date" name="birth_date" /></td>
								</c:forEach>
							</tbody>
						</table>
						<br /> <br /> <br />

						<div class='form-group'>
							<input type="submit" class="btn btn-primary btn-block"
								title="예약하가" value="예약하기" />
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
			if ($("#passenger_korean_name").val() == "") {
				alert("탑승객 한국성명을 입력해주세요.");
				$("#arrival_time").focus();
				return false;
			}
			if ($("#passenger_english_name").val() == "") {
				alert("탑승객 영문성명를 입력해주세요.");
				$("#number_of_seats").focus();
				return false;
			}
			if ($("#gender").val() == "") {
				alert("성별을 입력해주세요.");
				$("#gender").focus();
				return false;
			}
			if ($("#birth_date").val() == "") {
				alert("생년월일을 입력해주세요.");
				$("#birth_date").focus();
				return false;
			}
		});
	});
	function validateForm() {
		var telephoneRegex = /^01[016789]-[0-9]{4}-[0-9]{4}$/;
		if (!telephoneRegex.air($("#telephone_number").val())) {
			alert("010-xxxx-xxxx 형식으로 입력해주세요.");
			$("#telephone_number").focus();
			return false;
		}

		return true;
	}}
</script>
<!---------- END SCRIPT ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>