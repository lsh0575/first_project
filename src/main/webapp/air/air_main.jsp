<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="airMain2"
	class="container-field panel panel-default text-center">
	<div class="row">
		<div class="col-sm-12">
			<div class="airplane">
				<img class="width-full" width="100%"
					src="${pageContext.request.contextPath}/img/air.jpg" />
			</div>
			<div class="air_box">
				<div class="airFrom">
					<form action="${pageContext.request.contextPath}/air_uslist.air"
						method="get" id="form">
						<select class="airFrom_1" id="ticket_type" name="ticket_type" onchange="Blocking()">
							<option value="default">항공권 종류</option>
							<option value="왕복">왕복</option>
							<option value="편도">편도</option>
						</select> <input type="text" class="airFrom_2" id="start_point"
							name="start_point" list="start_point_list" placeholder="출발지" onclick="Blocking()" />
						<datalist id="start_point_list">
						</datalist>
						<input type="text" class="airFrom_2" id="end_point"
							name="end_point" list="end_point_list" placeholder="도착지" onclick="Blocking()" />
						<datalist id="end_point_list">
						</datalist>
						<input type="date" class="airFrom_2" id="start_date"
							name="start_date" oninput="timeInput(event)" placeholder="출국일" onclick="Blocking()"> <input type="date" class="airFrom_2"
							id="end_date" name="end_date" placeholder="귀국일" oninput="timeInput(event)" onclick="Blocking()">
						<input type="number" class="airFrom_2" id="adults" name="adults" min="0" max="9" placeholder="성인(16세이상)" onclick="Blocking()">
						<input type="number"  class="airFrom_2" id="teenagers" name="teenagers" min="0" max="9" placeholder="청소년(15세이하)" onclick="Blocking()">
						<input type="submit" class="airFrom_3" id="go"  value="검색" onclick="Blocking()" />
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
/********** 출발지 AJAX **********/
$(function () {
	$("#start_point").on("keyup", function() {
		$.ajax({
			url: "${pageContext.request.contextPath}/air.Avmain_start_pointjoin.air",
			type: "get",
			dataType: "json", // 데이터 타입을 json으로 변경
			data: { "start_point": $("#start_point").val() },
			success: function(json) {
				console.log(json[0]);
				$("#start_point_list").empty(); // 기존의 option 요소들을 삭제
				for (var i = 0; i < json.length; i++) {
					var start_point = json[i].start_point;
					var option = $("<option>").attr("value", start_point);
					$("#start_point_list").append(option);
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				$("#start_point").html(
					textStatus + "(HTTP-" + xhr.status + "/" + errorThrown);
			}
		});
	});
});

/********** 도착지 AJAX **********/
$(function() {
	$("#end_point").on("keyup", function() {
		$.ajax({
			url: "${pageContext.request.contextPath}/air.Avmain_end_pointjoin.air",
			type: "get",
			dataType: "json",
			data: { "end_point": $("#end_point").val() },
			success: function(json) {
				console.log(json[0]);
				$("#end_point_list").empty();
				for (var i = 0; i < json.length; i++) {
					var end_point = json[i].end_point;
					var option = $("<option>").attr("value", end_point);
					$("#end_point_list").append(option);
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				$("#end_point").val(textStatus + "(HTTP-" + xhr.status + "/" + errorThrown);
			}
		});
	});
});
	


/********** 출국일 및 귀국일 유효성 검사 **********/
const startDateInput = document.getElementById('start_date'); // 출국일
const endDateInput = document.getElementById('end_date'); // 귀국일

startDateInput.addEventListener('change', () => {
  const startDate = new Date(startDateInput.value);
  const endDate = new Date(endDateInput.value);

  if (endDate < startDate) {
    endDateInput.value = '';
    alert('출국일 다시 입력해주세요.');
  }
});

endDateInput.addEventListener('change', () => {
  const startDate = new Date(startDateInput.value);
  const endDate = new Date(endDateInput.value);

  if (endDate < startDate) {
    endDateInput.value = '';
    alert('귀국일 다시 입력해주세요.');
  }
});

function timeInput(event) {
  const inputDate = new Date(event.target.value);
  const today = new Date();

  if (inputDate < today) {
    event.target.value = '';
  }
}


	
/********** 편도 시 귀국일 disabled 및 비로그인 시 안됨 **********/

function Blocking() {
  var user = '<%= session.getAttribute("account") %>';
  const ticket_type = document.getElementById("ticket_type");
  const start_point = document.getElementById("start_point");
  const end_point = document.getElementById("end_point");
  const start_date = document.getElementById("start_date");
  const end_date = document.getElementById("end_date");
  const adults = document.getElementById("adults");
  const teenagers = document.getElementById("teenagers");
  const go = document.getElementById("go");

  if (user === 'null') {
	  alert("로그인 후 이용해주세요.");
	  ticket_type.disabled = true;
	  start_point.disabled = true;
	  end_point.disabled = true;
	  start_date.disabled = true;
	  end_date.disabled = true;
	  adults.disabled = true;
	  teenagers.disabled = true;	  
	  go.disabled = true;	  
	} else {
		  ticket_type.disabled = false;
		  start_point.disabled = false;
		  end_point.disabled = false;
		  start_date.disabled = false;
		  end_date.disabled = false;
		  adults.disabled = false;
		  teenagers.disabled = false;	  
		  go.disabled = false;	
		  if (ticket_type.value === "편도") {
			    end_date.disabled = true;
			  } else {
			    end_date.disabled = false;
			  }
	}
}

/********** 항목 선택 검사 **********/

$(document).ready(function() {
		$("#form").submit(function() {
				if($("#ticket_type").val() == "default") {
					alert("티켓 종류를 선택해주세요.");
					$("#ticket_type").focus();
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
				if($("#start_point").val() == $("#end_point").val()) {
					alert("출발지와 도착지가 같습니다.\n다시입력해주세요.");
					$("#start_point").focus();
					return false;
				}
				if($("#start_date").val() == "") {
					alert("출국일을 선택해주세요.");
					$("#start_date").focus();
					return false;
				}
				if($("#end_date").val() == "" && $("#ticket_type").val() != "편도") {
					alert("귀국일을 선택해주세요.");
					$("#end_date").focus();
					return false;
				}
				if($("#adults").val() == "") {
					alert("승객 수(성인 수)를 입력해주세요.");
					$("#adults").focus();
					return false;
				}
				if($("#teenagers").val() == "") {
					alert("승객 수(청소년 수)를 입력해주세요.");
					$("#teenagers").focus();
					return false;
				}
	});
});
</script> 