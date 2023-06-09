<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->
<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<!---------- START CONTAINER ---------->
<div id="airMain" class="body-top">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">항공편 등록</h3>
				<form action="${pageContext.request.contextPath}/air_avinsert.air"
					method="post" id="form">
					<table class="table table-striped table-bordered">
						<tbody>
							<tr>
								<th scope="row">항공편명</th>
								<td><input type="text" class="form-control" id="flight"
									name="flight" placeholder="항공편명을 입력하세요." /></td>
							</tr>
							<tr>
								<th scope="row">항공사</th>
								<td><input type="text" class="form-control" id="airline"
									name="airline" placeholder="항공사를 입력하세요." /></td>
							</tr>
							<tr>
								<th scope="row">분류</th>
								<td><select class="form-control"
									id="classification_of_flights" name="classification_of_flights">
										<option value="국내선">국내선</option>
										<option value="국제선">국제선</option>
								</select></td>
							</tr>
							<tr>
								<th scope="row">출발시간</th>
								<td><input type="datetime-local" class="form-control"
									oninput="timeInput(event)" id="departure_time"
									name="departure_time" /></td>
							</tr>
							<tr>
								<th scope="row">도착시간</th>
								<td><input type="datetime-local" class="form-control" oninput="timeInput(event)"
									id="arrival_time" name="arrival_time" /></td>
							</tr>
							<tr>
								<th scope="row">좌석 수</th>
								<td><input type="text" class="form-control"
									id="number_of_seats" name="number_of_seats"
									oninput="validateInput(event)" placeholder="좌석 수를 입력하세요." /></td>
							</tr>
							<tr>
								<th scope="row">좌석 등급</th>
								<td><input type="text" class="form-control" id="seat_class"
									name="seat_class" placeholder="좌석 등급을 입력하세요." /></td>
							</tr>
							<tr>
								<th scope="row">운임료</th>
								<td><input type="text" class="form-control"
									id="freight_charge" name="freight_charge"
									oninput="validateInput(event)" placeholder="운임료를 입력하세요." /></td>
							</tr>
							<tr>
								<th scope="row">출발지</th>
								<td><input type="text" class="form-control"
									id="start_point" name="start_point" placeholder="출발지를 입력하세요." /></td>
							</tr>
							<tr>
								<th scope="row">도착지</th>
								<td><input type="text" class="form-control" id="end_point"
									name="end_point" placeholder="도착지를 입력하세요." /></td>
							</tr>
						</tbody>
					</table>
					<div class='form-group'>
						<input type="submit" class="btn btn-primary btn-block" title="등록"
							value="등록" />
					</div>
					<div class="form-group">
						<input type="reset" class="btn btn-warning btn-block" title="취소"
							value="취소" />
					</div>
					<div class="form-group">
						<a href="javascript:history.go(-1)"
							class="btn btn-default btn-block" title="목록보기">목록보기</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- START JAVASCRIPT ---------->
<script>
const startDateInput = document.getElementById('departure_time');
const endDateInput = document.getElementById('arrival_time');

startDateInput.addEventListener('change', () => {
  const startDate = new Date(startDateInput.value);
  const endDate = new Date(endDateInput.value);

  if (endDate < startDate) {
    endDateInput.value = '';
    alert('출발시간 다시 입력해주세요.');
  }
});

endDateInput.addEventListener('change', () => {
  const startDate = new Date(startDateInput.value);
  const endDate = new Date(endDateInput.value);

  if (endDate < startDate) {
    endDateInput.value = '';
    alert('도착시간 다시 입력해주세요.');
  }
});

function validateInput(event) {
	  const numericPattern = /[^0-9]/g;

	  const input = event.target.value;
	  if (numericPattern.air(input)) {
	    event.target.value = input.replace(numericPattern, "");
	  }
	}
	
function timeInput(event) {
	  const inputDate = new Date(event.target.value);
	  const today = new Date();

	  if (inputDate < today) {
	    event.target.value = '';
	  }
}
	
$(document).ready(function() {
	$("#form").submit(function() {
		if($("#flight").val() == "") {
		alert("항공편명을 입력해주세요.");
		$("#flight").focus();
		return false;
	}
		if($("#airline").val() == "") {
			alert("항공사를 입력해주세요.");
			$("#airline").focus();
			return false;
		}
		if($("#departure_time").val() == "") {
			alert("출발시간을 입력해주세요.");
			$("#departure_time").focus();
			return false;
		}
		if($("#arrival_time").val() == "") {
			alert("도착시간을 입력해주세요.");
			$("#arrival_time").focus();
			return false;
		}
		if($("#number_of_seats").val() == "") {
			alert("좌석 수를 입력해주세요.");
			$("#number_of_seats").focus();
			return false;
		}
		if($("#seat_class").val() == "") {
			alert("좌석 등급을 입력해주세요.");
			$("#seat_class").focus();
			return false;
		}
		if($("#freight_charge").val() == "") {
			alert("운임료을 입력해주세요.");
			$("#freight_charge").focus();
			return false;
		}
		if($("#start_point").val() == "") {
			alert("출발지를 입력해주세요.");
			$("#start_point").focus();
			return false;
		}
		if($("#end_point").val() == "") {
			alert("도착지를 입력해주세요.");
			$("#end_point").focus();
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