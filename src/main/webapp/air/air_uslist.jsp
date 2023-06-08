<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->
<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->


<!---------- START CONTAINER ---------->
<div id='airMain' class="body-top container-field">
	<div class="row">
		<div class="airLeft col-sm-3">
			<div class="airL1">
				<form
					action="${pageContext.request.contextPath}/air_uslistAjax.air?adults=${param.adults}&teenagers=${param.teenagers}&ticket_type=${param.ticket_type}&start_date=${param.start_date}&end_date=${param.end_date}&start_point=${param.start_point}&end_point=${param.end_point}"
					method="post" id="form">
					<div class="menu-toggle" onclick="toggleMenu('menu')">
						<span class="menu-icon">가격순</span>
					</div>
					<nav class="menu">
						<ul>
							<li><input type="checkbox" id="low" name="price" value="low"
								checked onclick="checkOne(this)" /> <label for="low">최저가순</label></li>
							<li><input type="checkbox" id="high" name="price"
								value="high" onclick="checkOne(this)" /> <label for="high">최고가순</label>
							</li>
						</ul>
					</nav>
					<div class="menu-toggle">
						<span class="menu-icon">가격대순</span>
					</div>
					<nav class="menu">
						<ul>
							<li><input type="checkbox" id="by_price_all" name="by_price"
								value="by_price_all" checked onclick="checkTwo(this)" /> <label
								for="by_price_all">전체</label></li>
							<li><input type="checkbox" id="30" name="by_price"
								value="30" onclick="checkTwo(this)" /> <label for="30">30만원
									이내</label></li>
							<li><input type="checkbox" id="60" name="by_price"
								value="60" onclick="checkTwo(this)" /> <label for="60">30~60만원
									이내</label></li>
							<li><input type="checkbox" id="90" name="by_price"
								value="90" onclick="checkTwo(this)" /> <label for="90">60만원~90만원</label>
							</li>
							<li><input type="checkbox" id="91" name="by_price"
								value="91" onclick="checkTwo(this)" /> <label for="91">90만원이상
									이상</label></li>
						</ul>
					</nav>
					<div class="menu-toggle">
						<span class="menu-icon">출발시간</span>
					</div>
					<nav class="menu">
						<ul>
							<li><h5>가는날</h5></li>
							<li><input type="checkbox" id="departure_all"
								name="departure" value="departure_all"
								onclick="checkThree(this)" checked /> <label
								for="departure_all">전체</label></li>
							<li><input type="checkbox" id="departure_6" name="departure"
								value="departure_6" onclick="checkThree(this)" /> <label
								for="departure_6">00:00~06:00</label></li>
							<li><input type="checkbox" id="departure_12"
								name="departure" value="departure_12" onclick="checkThree(this)" />
								<label for="departure_12">06:00~12:00</label></li>
							<li><input type="checkbox" id="departure_18"
								name="departure" value="departure_18" onclick="checkThree(this)" />
								<label for="departure_18">12:00~18:00</label></li>
							<li><input type="checkbox" id="departure_24"
								name="departure" value="departure_24" onclick="checkThree(this)" />
								<label for="departure_18">18:00~24:00</label></li>
							<li><h5>오는날</h5></li>
							<li><input type="checkbox" id="coming_all" name="coming"
								value="coming_all" checked onclick="checkFour(this)" /> <label
								for="coming_all">전체</label></li>
							<li><input type="checkbox" id="coming_6" name="coming"
								value="coming_6" onclick="checkFour(this)" /> <label
								for="coming_6">00:00~06:00</label></li>
							<li><input type="checkbox" id="coming_12" name="coming"
								value="coming_12" onclick="checkFour(this)" /> <label
								for="coming_12">06:00~12:00</label></li>
							<li><input type="checkbox" id="departure_18" name="coming"
								value="coming_18" onclick="checkFour(this)" /> <label
								for="coming_18">12:00~18:00</label></li>
							<li><input type="checkbox" id="coming_24" name="coming"
								value="coming_24" onclick="checkFour(this)" /> <label
								for="coming_18">18:00~24:00</label></li>
						</ul>
					</nav>
					<div class="form-group">
						<input type="submit" class="b1 btn btn-primary form-control"
							id="where" value="검색" />
					</div>
				</form>
			</div>
		</div>

		<div id="aalist" class="col-sm-9">
			<div class="airP1">
				<h3 class="heading-panel">항공 검색</h3>
				<hr />
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- START SCRIPT ---------->
<script>
	/* 가격순 */
	function checkOne(element) {
		const price = document.getElementsByName("price");
		price.forEach((cb) => {
			cb.checked = false;
		});
		element.checked = true;
	}

	/* 가격대순 */
	function checkTwo(element) {
		const by_price = document.getElementsByName("by_price");
		by_price.forEach((cb) => {
			cb.checked = false;
		});
		element.checked = true;
	}
	
	/* 가는날 */
	function checkThree(element) {
		const departure = document.getElementsByName("departure");
		departure.forEach((cb) => {
			cb.checked = false;
		});
		element.checked = true;
	}
	
	/* 오는날 */
	function checkFour(element) {
		const coming = document.getElementsByName("coming");
		coming.forEach((cb) => {
			cb.checked = false;
		});
		element.checked = true;
	}	
	
$(document).ready(function() {
	/* 파싱 */
   var list = ${requestScope.list}; // dao.read(where)
   var airData = { // 출국 입국 비교하여 담을 데이터
    departure: [],
    entrance: []
  };
  
  var start_point = '${requestScope.start_point}';
  var end_point = '${requestScope.end_point}';
  var start_date = '${requestScope.start_date}';
  var end_date = '${requestScope.end_date}';
  var ticket_type = '${requestScope.ticket_type}';
 
  var size = Math.max(list.length);

  for (var i = 0; i < size; i++) {
	  console.log(list[i]);
    if (list[i].start_point == start_point && list[i].departure_time.includes(start_date)) { // 출국 데이터 담기
      airData.departure.push(list[i]);
     console.log(list[i]);
     console.log("출국");
    } else if (list[i].start_point == end_point && list[i].departure_time.includes(end_date)) { // 입국 데이터 담기
      airData.entrance.push(list[i]);
     console.log(list[i]);
	console.log("입국");
    }
  }
  
  var j1 = JSON.stringify({ // json.j1
    departure: airData.departure,
    entrance: airData.entrance
  });
  
  /* 데이터 추출 */
 var departure = JSON.parse(j1).departure;
 var entrance = JSON.parse(j1).entrance;
 
 var size = Math.max(departure.length, entrance.length);
 
 if (ticket_type == '왕복') {
	  for (var i = 0; i < size; i++) {
	    var aFlight_code = departure[i].flight_code;
	    var aFlight = departure[i].flight;
	    var aAirline = departure[i].airline;
	    var aClassification_of_flights = departure[i].classification_of_flights;
	    var aDeparture_time = departure[i].departure_time;
	    var aArrival_time = departure[i].arrival_time;
	    var aNumber_of_seats = departure[i].number_of_seats;
	    var aSeat_class = departure[i].seat_class;
	    var aFreight_charge = departure[i].freight_charge;
	    var aStart_point = departure[i].start_point;
	    var aEnd_point = departure[i].end_point;
	    var aAdministrator_ID = departure[i].administrator_ID;
	    var aCreation_date = departure[i].creation_date;
	    var aIp = departure[i].ip;

	    var bFlight_code = entrance[i].flight_code;
	    var bFlight = entrance[i].flight;
	    var bAirline = entrance[i].airline;
	    var bClassification_of_flights = entrance[i].classification_of_flights;
	    var bDeparture_time = entrance[i].departure_time;
	    var bArrival_time = entrance[i].arrival_time;
	    var bNumber_of_seats = entrance[i].number_of_seats;
	    var bSeat_class = entrance[i].seat_class;
	    var bFreight_charge = entrance[i].freight_charge;
	    var bStart_point = entrance[i].start_point;
	    var bEnd_point = entrance[i].end_point;
	    var bAdministrator_ID = entrance[i].administrator_ID;
	    var bCreation_date = entrance[i].creation_date;
	    var bIp = entrance[i].ip;

	    var di = $("<div class='airList'>");
	    var ul = $("<ul>");
	    var air = $("<li class='air'>");
	    var air2 = $("<li class='air'>");
	    var st1 = $("<li class='st1'>").text("출발");
	    var st2 = $("<li class='st2'>").text("도착");
	    var st3 = $("<li class='st1'>").text("출발");
	    var st4 = $("<li class='st2'>").text("도착");
	    var time = $("<li class='time'>");
	    var time2 = $("<li class='time'>");
	    var time3 = $("<li class='time'>");
	    var time4 = $("<li class='time'>");
	    var line = $("<li class='line'>");
	    var hr = $("<hr/>");
	    var b2 = $("<li class='b2'>");
	    var a = $("<a class='btn btn-danger' title='예약하기'>");
	    
	    var p1 = $("<p>").html(aAirline);
	    var p2 = $("<p>").html(aFlight);
	    var p3 = $("<p>").html(aStart_point);
	    var p4 = $("<p>").html(aDeparture_time);
	    var p5 = $("<p>").html(aEnd_point);
	    var p6 = $("<p>").html(aArrival_time);
	    var p7 = $("<p>").html(bAirline);
	    var p8 = $("<p>").html(bFlight);
	    var p9 = $("<p>").html(bStart_point);
	    var p10 = $("<p>").html(bDeparture_time);
	    var p11 = $("<p>").html(bEnd_point);
	    var p12 = $("<p>").html(bArrival_time);

	    air.append(p1).append(p2);
	    time.append(p3).append(p4);
	    time2.append(p5).append(p6);
	    line.append(hr);
	    air2.append(p7).append(p8);
	    time3.append(p9).append(p10);
	    time4.append(p11).append(p12);
	    var sum = (parseInt(aFreight_charge) + parseInt(bFreight_charge));
	   	a.attr("href", '${pageContext.request.contextPath}/air_usinsert_view.air?flight_code='+aFlight_code+'&flight_code='+bFlight_code+'&ticket_type=${param.ticket_type}'+'&adults='+${param.adults}+'&teenagers='+${param.teenagers}).text(sum.toString()+'원');
	    b2.append(a);
	    ul.append(air).append(st1).append(time).append(st2).append(time2).append(line).append(air2).append(st3).append(time3).append(st4).append(time4).append(b2);
	    di.append(ul);
	    $("#aalist .airP1").append(di);
	  }
	} else if (ticket_type == '편도') {
		  for (var i = 0; i < departure.length; i++) {
	    var aFlight_code = departure[i].flight_code;
	    var aFlight = departure[i].flight;
	    var aAirline = departure[i].airline;
	    var aClassification_of_flights = departure[i].classification_of_flights;
	    var aDeparture_time = departure[i].departure_time;
	    var aArrival_time = departure[i].arrival_time;
	    var aNumber_of_seats = departure[i].number_of_seats;
	    var aSeat_class = departure[i].seat_class;
	    var aFreight_charge = departure[i].freight_charge;
	    var aStart_point = departure[i].start_point;
	    var aEnd_point = departure[i].end_point;
	    var aAdministrator_ID = departure[i].administrator_ID;
	    var aCreation_date = departure[i].creation_date;
	    var aIp = departure[i].ip;
	    
	    var di = $("<div class='airList'>");
	    var ul = $("<ul>");
	    var air = $("<li class='air'>");
	    var st1 = $("<li class='st1'>").text("출발");
	    var st2 = $("<li class='st2'>").text("도착");
	    var time = $("<li class='time'>");
	    var time2 = $("<li class='time'>");
	    var line = $("<li class='line'>");
	    var b2 = $("<li class='b2'>");
	    var a = $("<a class='btn btn-danger' title='예약하기'>");
	    
	    var p1 = $("<p>").html(aAirline);
	    var p2 = $("<p>").html(aFlight);
	    var p3 = $("<p>").html(aStart_point);
	    var p4 = $("<p>").html(aDeparture_time);
	    var p5 = $("<p>").html(aEnd_point);
	    var p6 = $("<p>").html(aArrival_time);

	    air.append(p1).append(p2);
	    time.append(p3).append(p4);
	    time2.append(p5).append(p6);
	    var sum = parseInt(aFreight_charge);
	   	a.attr("href", '${pageContext.request.contextPath}/air_usinsert_view.air?flight_code='+aFlight_code+'&ticket_type=${param.ticket_type}'+'&adults='+${param.adults}+'&teenagers='+${param.teenagers}).text(sum.toString()+'원');
	    b2.append(a);
	    ul.append(air).append(st1).append(time).append(st2).append(time2).append(b2);
	    di.append(ul);
	    $("#aalist .airP1").append(di);
		  }
	}
}); 
</script>
<!---------- END SCRIPT ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>